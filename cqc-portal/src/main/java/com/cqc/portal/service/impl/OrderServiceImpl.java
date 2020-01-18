package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.Order;
import com.cqc.portal.mapper.OrderMapper;
import com.cqc.portal.service.OrderService;
import org.springframework.stereotype.Service;

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

}
