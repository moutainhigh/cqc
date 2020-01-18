package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.Order;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void autoOrder(String userId) {

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
