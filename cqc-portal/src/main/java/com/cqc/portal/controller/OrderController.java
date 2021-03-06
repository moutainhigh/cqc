package com.cqc.portal.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.*;
import com.cqc.portal.dto.OrderQuery;
import com.cqc.portal.service.*;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private UserFundService userFundService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRealInfoService userRealInfoService;

    @Autowired
    private ReceiveCodeService receiveCodeService;

    @ApiOperation("我的订单")
    @GetMapping("/list")
    public Result<Page<Order>> list(OrderQuery param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        Date now = new Date();
        Date startDate = null;
        Date endDate = null;
        try {
            if (!StringUtils.isEmpty(param.getStartTimeStr())) {
                startDate = DateUtil.parse(param.getStartTimeStr(), "yyyy-MM-dd");
            }else {
                startDate = DateUtil.beginOfDay(now);

            }
        } catch (Exception e) {
            endDate = null;
        }
        try {
            if (!StringUtils.isEmpty(param.getEndTimeStr())) {
                endDate = DateUtil.parse(param.getEndTimeStr(), "yyyy-MM-dd");
            } else {
                endDate = DateUtil.endOfDay(now);
            }
        } catch (Exception e) {
            endDate = null;
        }
        if (Objects.equals(0, param.getStatus())) {
            param.setStatus(null);
        }
        Page<Order> page = new Page<>(param.getPageNum(), param.getPageSize());
        orderService.page(page, new QueryWrapper<Order>()
                .eq("user_id", userId)
                .eq(param.getStatus() != null, "status", param.getStatus())
                .eq(param.getChannel() != null, "channel", param.getChannel())
                .gt(startDate != null, "create_time", startDate)
                .lt(endDate != null, "create_time", endDate)
                .orderByDesc("create_time"));
        return Result.success(page);
    }

    @ApiOperation("获取新订单")
    @GetMapping("/getNew")
    public Result<List<Order>> getNew(OrderQuery param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        List<Order> list = orderService.list(new QueryWrapper<Order>()
                .eq("user_id", userId)
                .eq("status", 0)
                //.gt(!StringUtils.isEmpty(param.getStartTimeStr()), "create_time", param.getStartTimeStr())
                .orderByDesc("create_time"));
        return Result.success(list);
    }


    @ApiOperation("确认付款")
    @GetMapping("/confirmPay")
    public Result<List<Order>> confirmPay(@NotBlank(message = "id不能为空") String id) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        boolean rs = orderService.confirmPay(userId, id);
        if (!rs) {
            return Result.failed("确认失败");
        }
        return Result.success();
    }

    @ApiOperation(value = "自动抢单")
    @GetMapping("/buyOrder")
    public Result<Boolean> startAutoOrder() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        // 查实名信息
        UserRealInfo realInfo = userRealInfoService.getRealInfo(userId);
        if (realInfo == null) {
            // 如果没实名 不允许抢单
            throw new BaseException(BaseErrorMsg.NOT_REAL);
        }
        if (StringUtils.isEmpty(user.getGoogleSecret())) {
            throw new BaseException(BaseErrorMsg.NO_BIND_GOODS_SECRET);
        }
        List<ReceiveCode> list = receiveCodeService.list(new QueryWrapper<ReceiveCode>().eq("user_id", userId)
                .eq("status", 1));
        //未上传收款码
        if (CollectionUtils.isEmpty(list)) {
            throw new BaseException(BaseErrorMsg.NO_RECEIVE_CODE);
        }
        orderService.autoOrder(user, list);

        return Result.success(true, "抢单成功"+System.currentTimeMillis());
    }


}

