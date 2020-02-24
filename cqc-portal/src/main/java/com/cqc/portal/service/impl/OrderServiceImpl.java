package com.cqc.portal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.constant.Constants;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.common.utils.OrderUtils;
import com.cqc.model.*;
import com.cqc.portal.dto.resp.CityHotDataDto;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.mapper.OrderPublishMapper;
import com.cqc.portal.service.*;
import com.cqc.portal.utils.EhcacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

@Slf4j
@EnableAsync
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderPublishService orderPublishService;

    @Autowired
    private OrderPublishMapper orderPublishMapper;

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

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Autowired
    private PddAccountService pddAccountService;

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

        List<PddAccount> pddList = new ArrayList<>();
        List<PddAccount> pddAccountList = pddAccountService.getByUser(user.getId());


        if (!CollectionUtils.isEmpty(pddAccountList)) {
            pddAccountList.forEach(item -> {
                if (item.getType() == 1 && fund.getPddSeller().compareTo(BigDecimal.ZERO) > 0) {
                    pddList.add(item);
                }
                if (item.getType() == 2 && fund.getPddBuyer().compareTo(BigDecimal.ZERO) > 0) {
                    pddList.add(item);
                }
            });
        }
        pddList.forEach(item -> {
            if (item.getType() == 1) {
                channelSet.add(3);
            } else if (item.getType() == 2) {
                channelSet.add(4);
            }
        });

        // 抢单限制 用户只要能用70%余额抢单
        BigDecimal availableBalance = fund.getAvailableBalance().multiply(Constants.BUY_ORDER_PERCENT);
        if (availableBalance.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        // 查询金额足够支付的订单
        OrderPublish order = orderPublishService.selectNewOrder(availableBalance, channelSet);
        if (order == null) {
            // 没有可抢的订单
            return;
        }
        // 过滤
        List<ReceiveCode> collect = list.stream().filter(item -> {return item.getChannel().equals(order.getChannel());
        }).collect(Collectors.toList());

        ReceiveCode receiveCode = new ReceiveCode();
        PddAccount pddAccount = new PddAccount();

        if (!CollectionUtils.isEmpty(collect)) {
            if (collect.size() > 1) {
                receiveCode = collect.get(new Random().nextInt(collect.size()));
            } else {
                receiveCode = collect.get(0);
            }
        }

        if (!CollectionUtils.isEmpty(pddList)) {
            if (pddList.size() > 1) {
                pddAccount = pddList.get(new Random().nextInt(pddList.size()));
            } else {
                pddAccount = pddList.get(0);
            }
        }
        // 查询该二维码和该金额是否存在未支付的订单
        Integer ct = orderMapper.countByReceiveId(receiveCode.getId(), order.getAmount());
        if (ct != null && ct > 0) {
            log.info("该收款码 不能重复抢 同样金额的单");
            return;
        }
        buyOrder(user, order.getId(), receiveCode, pddAccount);
    }


    @Override
    public void autoCancel() {
        // 查询15分钟前，且未确认收款的订单
        List<Order> orders = orderMapper.payTimeOutList();
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        for (Order order : orders) {
            cancel(order);
        }
    }


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean cancel(Order order) {
        // 再一次判断订单的状态
        Order record = orderMapper.selectById(order.getId());
        if (record.getStatus() != 0) {
            return false;
        }
        // 查询放单
        OrderPublish orderPublish = orderPublishService.getById(order.getPublishOrderId());
        if (orderPublish == null || orderPublish.getStatus() != 0) {
            return false;
        }

        // 将用户冻结金额解冻
        boolean b = userFundService.unFreezeBalance(order.getUserId(), order.getAmount());
        if (!b) {
            return false;
        }
        // 修改订单状态
        Order entity = new Order();
        entity.setId(order.getId());
        entity.setStatus(3);
        entity.setCancelTime(new Date());
        int i = orderMapper.updateById(entity);
        if (i != 1) {
            throw new BaseException("", "取消订单失败");
        }
        // 修改放单状态
        if (!StringUtils.isEmpty(order.getPublishOrderId())) {
            OrderPublish entity2 = new OrderPublish();
            entity2.setId(order.getPublishOrderId());
            entity2.setStatus(-1);
            orderPublishService.updateById(entity2);
        }
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean confirmPay(String userId, String id) {
        // 查询订单
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new BaseException("", "订单错误");
        }
        if (order.getStatus() == 3) {
            throw new BaseException("", "订单已取消，无法付款");
        }
        if (order.getStatus() > 0) {
            throw new BaseException("", "重复付款");
        }

        // 将订单状态改为确认付款
        Order entity = new Order();
        entity.setId(id);
        entity.setStatus(1);
        entity.setPayTime(new Date());
        entity.setAmount(null);
        int i = orderMapper.updateById(entity);

        OrderPublish entity2 = new OrderPublish();
        entity2.setId(order.getPublishOrderId());
        entity2.setStatus(1);
        entity2.setPayTime(new Date());
        orderPublishService.updateById(entity2);

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
     *
     * @param user
     * @return
     */

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public void buyOrder(User user, String orderId, ReceiveCode receiveCode, PddAccount pddAccount) {
        // 再次查询数据库
        OrderPublish orderPublish = orderPublishMapper.selectById(orderId);
        if (orderPublish == null || orderPublish.getStatus() != -1) {
            return;
        }
        // 冻结金额
        boolean b = userFundService.freezeBalance(user.getId(), orderPublish.getAmount());
        if (!b) {
            throw new BaseException("", "可用余额不足，不足以抢单");
        }
        // 读取费率
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal rate = rateService.getRate(orderPublish.getChannel());
        int count = userRecommendService.count(new QueryWrapper<UserRecommend>().eq("user_id", user.getId()));
        BigDecimal a = new BigDecimal("0.001").multiply(new BigDecimal(count));
        rate = rate.subtract(a);
        if (rate.compareTo(BigDecimal.ZERO) > 0) {
            // 如果佣金费率大于0
            income = orderPublish.getAmount().multiply(rate);
        }
        Order entity = new Order();
        entity.setOrderSn(OrderUtils.generatePublishOrderSn());

        entity.setPublishOrderSn(orderPublish.getOrderSn());
        entity.setPublishOrderId(orderId);
        entity.setPublisher(orderPublish.getPublisher());
        entity.setAmount(orderPublish.getAmount());
        entity.setChannel(orderPublish.getChannel());
        entity.setStatus(0);
        entity.setUserId(user.getId());
        entity.setBuyTime(new Date());
        entity.setIncome(income);
        entity.setRate(rate);
        entity.setAccount(user.getAccount());
        entity.setReceiveCodeId(receiveCode.getId());
        entity.setReceiveCodeImg(receiveCode.getCodeImg());
        entity.setReceiveCodeName(receiveCode.getReceiveName());
        entity.setCountry(user.getCountry());
        entity.setProvince(user.getProvince());
        entity.setCity(user.getCity());
        entity.setRegion(user.getRegion());
        entity.setPddAccountId(pddAccount.getId());
        entity.setPddAccount(pddAccount.getPddAccount());

        orderMapper.insert(entity);
        // todo 加锁
        int i = orderPublishMapper.updateStatus(orderId, 0);
        if (i != 1) {
            // 抢单失败
            throw new BaseException("", "抢单失败，回滚");
        }
        // 抢单成功  存放标记到 ecache中s
        ehcacheUtil.set(Constants.NEW_ORDER_PREFIX + user.getId(), "1");
    }

    @Override
    public BigDecimal getWaitPayIncome(String userId) {
        // 直接从数据库中查
        return orderMapper.waitPayIncome(userId);
    }


    @Override
    public List<CityHotDataDto> cityHot() {
        return orderMapper.cityHot();
    }

}
