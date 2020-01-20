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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        Order order = orderMapper.selectNewOrder();
        buyOrder(userId, order.getId());
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

        // 将订单状态改为确认付款
        Order entity = new Order();
        entity.setId(id);
        entity.setStatus(1);
        entity.setPayTime(new Date());
        entity.setAmount(null);
        int i = orderMapper.updateById(entity);
        // 保存佣金记录
        userFundService.addBalance(userId, order.getCommission(), 1, "抢单佣金");
        // 保存
        return i == 1;
    }

    /**
     * 下单
     * @param userId
     * @return
     */

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public void buyOrder(String userId, String orderId) {

        // 再次查询数据库
        Order order = orderMapper.selectById(orderId);
        if (order == null || order.getStatus() != 0) {
            return;
        }
        // 读取费率
        BigDecimal commission = BigDecimal.ZERO;
        BigDecimal rate = userRateService.getRate(userId, order.getChannel());
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            // 如果佣金费率大于0
            commission = order.getAmount().multiply(rate);
        }
        // todo 加锁
        int i = orderMapper.buyOrder(userId, orderId, new Date(), commission);
    }
}
