package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.mapper.OrderMapper;
import com.cqc.admin.service.OrderService;
import com.cqc.model.Order;
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
