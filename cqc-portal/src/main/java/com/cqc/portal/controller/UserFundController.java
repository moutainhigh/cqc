package com.cqc.portal.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.constant.Constants;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserFund;
import com.cqc.model.UserFundRecord;
import com.cqc.portal.dto.FundRecordQueryParam;
import com.cqc.portal.dto.resp.UserFundDto;
import com.cqc.portal.service.UserFundRecordService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserService;
import com.cqc.portal.service.UserVirtualFundService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户-虚拟币资产 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/userFund")
public class UserFundController {

    @Autowired
    private UserFundService service;

    @Autowired
    private UserFundRecordService userFundRecordService;

    @Autowired
    private UserService userService;

    @ApiOperation("缴纳做单押金")
    @GetMapping("/payDeposit")
    public Result<Boolean> payDeposit(@NotBlank(message = "type不能为空") Integer type){
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        boolean rs = false;
        if (type == 1) {
            rs = service.deposit(userId, 1, Constants.DEPOSIT_SELLER);
        } else if (type == 2) {
            rs = service.deposit(userId, 2, Constants.DEPOSIT_BUYER);
        }
        if (!rs) {
            return Result.failed("缴纳押金失败");
        }
        return Result.success(true);
    }


    @ApiOperation("账变记录")
    @GetMapping("/myList")
    public Result<Page<UserFundRecord>> myList(FundRecordQueryParam param) {
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
            } else {
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
        if (Objects.equals(0, param.getType())) {
            param.setType(null);
        }
        Page<UserFundRecord> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper wrapper = new QueryWrapper<UserFundRecord>().eq("user_id", userId)
                .eq(param.getType() != null, "type", param.getType())
                .gt(startDate != null, "create_time", startDate)
                .lt(endDate != null, "create_time", endDate)
                .orderByDesc("create_time");

        userFundRecordService.page(page, wrapper);

        return Result.success(page);
    }

    @ApiOperation("用户资金")
    @GetMapping("/getBalance")
    public Result<UserFundDto> getFund(){
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        UserFundDto userFundDto = new UserFundDto();
        // 查余额
        UserFund fund = service.getFund(userId);
        userFundDto.setCqc(fund.getAvailableBalance());

        return Result.success(userFundDto);
    }

}

