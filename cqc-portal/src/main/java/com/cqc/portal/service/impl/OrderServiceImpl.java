package com.cqc.portal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.*;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    private UserFundService userFundService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserRecommendService userRecommendService;

    @Autowired
    private UserDateIncomeService userDateIncomeService;

    @Autowired
    private UserService userService;

    @Async
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void autoOrder(User user, List<ReceiveCode> list) {
        UserFund fund = userFundService.getFund(user.getId());
        if (fund.getAvailableBalance().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BaseException(BaseErrorMsg.BALANCE_LESS);
        }
        // 查询符合该用户收款渠道的可抢单
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Set<Integer> channelSet = new HashSet<>();
        list.stream().forEach(item -> {
            channelSet.add(item.getChannel());
        });
        BigDecimal availableBalance = fund.getAvailableBalance();
        // 查询金额足够支付的订单
        Order order = orderMapper.selectNewOrder(availableBalance, channelSet);
        if (order == null) {
            // 没有可抢的订单
            return;
        }
        buyOrder(user, order.getId(), list);
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
        boolean rs = userFundService.cutFreezeBalance(userId, order.getAmount(),6 , "确认付款");
        if (!rs) {
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
        userFundService.addIncome(userId, order.getIncome());
        // 保存上级佣金记录
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        userDateIncomeService.saveUserIncome(userId, order.getAmount(), date);
        // 保存
        return i == 1;
    }

    /**
     * 下单
     * @param user
     * @return
     */

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public void buyOrder(User user, String orderId, List<ReceiveCode> receiveCodeList) {
        // 再次查询数据库
        Order order = orderMapper.selectById(orderId);
        if (order == null || order.getStatus() != -1) {
            return;
        }
        // 取一个收款码
        List<ReceiveCode> collect = receiveCodeList.stream().filter(item -> {
            return item.getChannel().equals(order.getChannel());
        }).collect(Collectors.toList());
        // 得到一个收款码
        ReceiveCode receiveCode = collect.get(0);
        // 冻结金额
        boolean b = userFundService.freezeBalance(user.getId(), order.getAmount());
        if (!b) {
            throw new BaseException("", "可用余额不足，不足以抢单");
        }
        // 读取费率
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal rate = rateService.getRate(order.getChannel());
        int count = userRecommendService.count(new QueryWrapper<UserRecommend>().eq("user_id", user.getId()));
        BigDecimal a = new BigDecimal("0.001").multiply(new BigDecimal(count));
        rate = rate.subtract(a);
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            // 如果佣金费率大于0
            income = order.getAmount().multiply(rate);
        }
        Order entity = new Order();
        entity.setId(orderId);
        entity.setStatus(0);
        entity.setUserId(user.getId());
        entity.setBuyTime(new Date());
        entity.setIncome(income);
        entity.setRate(rate);
        entity.setAccount(user.getAccount());
        entity.setReceiveCodeId(receiveCode.getId());
        entity.setReceiveCodeImg(receiveCode.getCodeImg());
        // todo 加锁
        int i = orderMapper.update(entity, new QueryWrapper<Order>().eq("id", orderId)
                .eq("status", -1));
        if (i != 1) {
            // 抢单失败
            throw new BaseException("", "抢单失败，回滚");
        }
    }


    @Override
    public BigDecimal getWaitPayIncome(String userId) {
        // 直接从数据库中查
        return orderMapper.waitPayIncome(userId);
    }

}
