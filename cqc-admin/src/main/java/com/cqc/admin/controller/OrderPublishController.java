package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.OrderPublishParam;
import com.cqc.admin.dto.OrderQueryParam;
import com.cqc.admin.service.OrderPublishService;
import com.cqc.common.api.Result;
import com.cqc.common.utils.OrderUtils;
import com.cqc.model.Order;
import com.cqc.model.OrderPublish;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-02-15
 */
@RestController
@RequestMapping("/orderPublish")
public class OrderPublishController {


    @Autowired
    private OrderPublishService service;

    @ApiOperation("已放单列表")
    @GetMapping("/list")
    public Result<Page<OrderPublish>> list(OrderQueryParam params) {

        Page<OrderPublish> page = new Page<>(params.getPageNum(), params.getPageSize());

        QueryWrapper<OrderPublish> wrapper = new QueryWrapper<OrderPublish>()
                .eq(params.getStatus() != null, "status", params.getStatus())
                .eq(params.getOrderSn() != null, "order_sn", params.getOrderSn())
                .orderByDesc("create_time");

        service.page(page, wrapper);
        return Result.success(page);
    }


    @ApiOperation("手动放单")
    @PostMapping("/push")

    public Result<OrderPublish> push(@Validated @RequestBody OrderPublishParam param) {
        OrderPublish order = new OrderPublish();
        BeanUtils.copyProperties(param, order);

        // 设置订单号
        order.setOrderSn(OrderUtils.generateOrderSn());
        order.setStatus(-1);

        boolean rs = service.save(order);
        if (!rs) {
            return Result.failed("放单失败");
        }
        return Result.success(order);
    }


}

