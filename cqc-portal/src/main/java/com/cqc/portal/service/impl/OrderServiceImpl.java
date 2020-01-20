package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Order;
import com.cqc.model.UserFund;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.mapper.UserVirtualFundMapper;
import com.cqc.portal.service.OrderService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserRateService;
import com.cqc.portal.service.UserVirtualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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


@EnableAsync
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserRateService userRateService;

    @Autowired
    private UserFundService userFundService;


    @Async
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void autoOrder(String userId) {
        UserFund fund = userFundService.getFund(userId);
        if (fund.getAvailableBalance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BaseException(BaseErrorMsg.BALANCE_LESS);
        }
        BigDecimal availableBalance = fund.getAvailableBalance();
        // 查询金额足够支付的订单
        Order order = orderMapper.selectNewOrder(availableBalance);
        if (order == null) {
            // 没有可抢的订单
            return;
        }
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
        boolean b = userFundService.cutFreezeBalance(userId, order.getAmount(),6 , "确认付款");
        if (!b) {
            return false;
        }
        // 将订单状态改为确认付款
        Order entity = new Order();
        entity.setId(id);
        entity.setStatus(1);
        entity.setPayTime(new Date());
        entity.setAmount(null);
        int i = orderMapper.updateById(entity);
        // 保存佣金记录
        userFundService.addBalance(userId, order.getCommission(), 7, "抢单佣金");
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
        if (order == null || order.getStatus() != -1) {
            return;
        }
        // 冻结金额
        boolean b = userFundService.freezeBalance(userId, order.getAmount());
        if (!b) {
            throw new BaseException("", "可用余额不足，不足以抢单");
        }
        // 读取费率
        BigDecimal commission = BigDecimal.ZERO;
        BigDecimal rate = userRateService.getRate(userId, order.getChannel());
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            // 如果佣金费率大于0
            commission = order.getAmount().multiply(rate);
        }
        // todo 加锁
        int i = orderMapper.buyOrder(orderId, userId, new Date(), commission);
        if (i != 1) {
            // 抢单失败
            throw new BaseException("", "抢单失败，回滚");
        }
    }

}
