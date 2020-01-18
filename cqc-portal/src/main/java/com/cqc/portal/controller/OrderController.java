package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Order;
import com.cqc.portal.service.OrderService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
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

    @ApiOperation("我的订单")
    @GetMapping("/list")
    public Result<Page<Order>> list(PageQuery pageQuery, String status) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        Page<Order> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        orderService.page(page, new QueryWrapper<Order>()
                .eq("user_id", userId)
                .eq(!StringUtils.isEmpty(status), "status", status)
                .orderByDesc("create_time"));
        return Result.success(page);
    }





}

