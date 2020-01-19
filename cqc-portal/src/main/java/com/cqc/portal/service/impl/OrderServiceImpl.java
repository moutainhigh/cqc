package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Order;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.mapper.UserVirtualFundMapper;
import com.cqc.portal.service.OrderService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserRateService;
import com.cqc.portal.service.UserVirtualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserVirtualFundService userVirtualFundService;

    @Autowired
    private UserRateService userRateService;

    @Autowired
    private UserFundService userFundService;

    @Override
    public void autoOrder(String userId) {

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean confirmPay(String userId, String id) {
        // 查询订单
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new BaseException("", "订单错误");
        }
        if (order.getStatus() > 0) {
            throw new BaseException("", "重复付款");
        }
        // 获取订单金额  将用户cqc冻结金额减少
        boolean b = userVirtualFundService.cutFreezeBalance(userId, 1, order.getAmount(), "");
        // 计算订单的佣金
        BigDecimal commission = BigDecimal.ZERO;
        BigDecimal rate = userRateService.getRate(userId, order.getChannel());
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            // 如果佣金费率大于0
            commission = order.getAmount().multiply(rate);
        }
        // 将订单状态改为确认付款
        Order entity = new Order();
        entity.setId(id);
        entity.setStatus(1);
        entity.setPayTime(new Date());
        entity.setCommission(commission);
        int i = orderMapper.updateById(entity);
        // 保存佣金记录
        userFundService.addBalance(userId, commission, 1, "抢单佣金");
        // 保存
        return i == 1;
    }

    /**
     * 下单
     * @param userId
     * @return
     */

    @Transactional(rollbackFor = Throwable.class)
    public void buyOrder(String userId) {
        // 读取最新发布的一个订单，然后尝试抢单
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("status", 0)
                .orderByDesc("create_time"));
        if (order == null) {
            return;
        }
        orderMapper.buyOrder(userId, order.getOrderSn());
    }
}
