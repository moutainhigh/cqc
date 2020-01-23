package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.OrderPublishParam;
import com.cqc.admin.dto.OrderQueryParam;
import com.cqc.admin.service.OrderService;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.common.utils.OrderUtils;
import com.cqc.model.Faq;
import com.cqc.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *
 * 订单 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */

@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("已放单列表")
    @GetMapping("/list")
    public Result<Page<Order>> list(OrderQueryParam params) {

        Page<Order> page = new Page<>(params.getPageNum(), params.getPageSize());

        QueryWrapper<Order> wrapper = new QueryWrapper<Order>()
                .eq(params.getStatus() != null, "status", params.getStatus())
                .orderByDesc("create_time");

        orderService.page(page, wrapper);
        return Result.success(page);
    }


    @ApiOperation("手动放单")
    @PostMapping("/push")
    public Result<Order> push(@Validated @RequestBody OrderPublishParam param) {
        Order order = new Order();
        BeanUtils.copyProperties(param, order);

        // 设置订单号
        order.setOrderSn(OrderUtils.generateOrderSn());
        order.setStatus(-1);

        boolean rs = orderService.save(order);
        if (!rs) {
            return Result.failed("放单失败");
        }
        return Result.success(order);
    }



}

