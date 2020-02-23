package com.cqc.portal.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.constant.Constants;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.model.UserFund;
import com.cqc.model.UserRealInfo;
import com.cqc.model.UserVirtualFund;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import com.cqc.portal.dto.resp.UserFundDto;
import com.cqc.portal.dto.resp.UserInfo;
import com.cqc.portal.service.*;
import com.cqc.portal.utils.EhcacheUtil;
import com.cqc.security.util.GoogleAuthUtil;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Slf4j
@Api(tags = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRealInfoService userRealInfoService;

    @Autowired
    private UserFundService userFundService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDateIncomeService userDateIncomeService;

    @Autowired
    private ReceiveCodeService receiveCodeService;

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @ApiOperation("登录用户数据")
    @GetMapping("/getInfo")
    public Result<UserInfo> getInfo() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(PortalUserUtil.getCurrentUsername());
        // 查实名信息
        UserRealInfo realInfo = userRealInfoService.getRealInfo(userId);
        if (realInfo != null) {
            userInfo.setReal(1);
            userInfo.setRealName(realInfo.getRealName());
        }
        // 查余额
        UserFund fund = userFundService.getFund(userId);
        userInfo.setCqcTotal(fund.getAvailableBalance());
        userInfo.setCqc(fund.getAvailableBalance().multiply(Constants.BUY_ORDER_PERCENT));
        userInfo.setPddSeller(fund.getPddSeller());
        userInfo.setPddBuyer(fund.getPddBuyer());
        // 查待入cqc，今日已入收益
        userInfo.setWaitPayIncome(orderService.getWaitPayIncome(userId));
        userInfo.setIncomeToday(userDateIncomeService.getIncomeByDate(userId, DateUtil.format(new Date(), "yyyyMMdd")));
        return Result.success(userInfo);
    }

    @ApiOperation("获取用户数据")
    @GetMapping("/userInfo")
    public Result<UserInfo> userInfo() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        UserInfo userInfo = new UserInfo();
        User user = userService.getUser(userId);

        if (user == null) {
            return Result.failed();
        }
        BeanUtils.copyProperties(user, userInfo);
        if (!StringUtils.isEmpty(user.getGoogleSecret())) {
            userInfo.setGoogleBind(1);
        }
        return Result.success(userInfo);
    }


    @ApiOperation(value = "修改登录密码")
    @PostMapping("/modifyLoginPwd")
    public Result<Boolean> modifyLoginPwd(@RequestBody ModifyPasswordParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        // 校验谷歌验证码
        long t = System.currentTimeMillis();
        boolean b = GoogleAuthUtil.checkCode(param.getCode(), user.getGoogleSecret(), t);
        log.info("谷歌验证， 账号 : {}, 密钥: {}, code ： {}", user.getId(), user.getGoogleSecret(), param.getCode());
        if (!b) {
            throw new BaseException(BaseErrorMsg.GOOGLE_CODE_ERROR);
        }
        boolean rs = userService.modifyLoginPwd(userId, param);
        if (!rs) {
            return Result.failed("修改登录密码失败");
        }
        return Result.success();
    }


    @ApiOperation(value = "修改支付密码")
    @PostMapping("/modifyPayPwd")
    public Result<Boolean> modifyPayPwd(@RequestBody ModifyPasswordParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        // 校验谷歌验证码
        long t = System.currentTimeMillis();
        boolean b = GoogleAuthUtil.checkCode(param.getCode(), user.getGoogleSecret(), t);
        log.info("谷歌验证， 账号 : {}, 密钥: {}, code ： {}", user.getId(), user.getGoogleSecret(), param.getCode());
        if (!b) {
            throw new BaseException(BaseErrorMsg.GOOGLE_CODE_ERROR);
        }
        boolean rs = userService.modifyPayPwd(userId, param);
        if (!rs) {
            return Result.failed("修改支付密码失败");
        }
        return Result.success();
    }


    @ApiOperation(value = "修改地区")
    @PostMapping("/modifyArea")
    public Result<Boolean> modifyArea(@RequestBody ModifyAreaParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        boolean rs = userService.modifyArea(userId, param);
        if (!rs) {
            return Result.failed("修改地区失败");
        }
        return Result.success();
    }

    @ApiOperation(value = "关闭自动抢单")
    @GetMapping("/openAutoBuy")
    public Result<Boolean> openAutoBuy() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        if (user.getAutoOrderStatus()) {
            return Result.failed(BaseErrorMsg.ALREADY_AUTO_BUY);
        }
        UserFund fund = userFundService.getFund(userId);
        if (fund.getAvailableBalance().compareTo(BigDecimal.ZERO) <= 0) {
            return Result.failed(BaseErrorMsg.BALANCE_LESS);
        }
        // 查实名信息
        UserRealInfo realInfo = userRealInfoService.getRealInfo(userId);
        if (realInfo == null) {
            // 如果没实名 不允许抢单
            return Result.failed(BaseErrorMsg.NOT_REAL);
        }
        //未上传收款码
        int codeNumber = receiveCodeService.getCodeNumber(userId);
        if (codeNumber <= 0) {
            return Result.failed(BaseErrorMsg.NO_RECEIVE_CODE);
        }
        boolean b = userService.openCloseAutoOrder(userId, 1);
        if (!b) {
            return Result.failed("开启自动抢单失败");
        }
        return Result.success();
    }

    @ApiOperation(value = "关闭自动抢单")
    @GetMapping("/closeAutoBuy")
    public Result<Boolean> closeAutoBuy() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        boolean b = userService.openCloseAutoOrder(userId, 0);
        if (!b) {
            return Result.failed("关闭自动抢单失败");
        }
        return Result.success();
    }

    @ApiOperation(value = "查询用户资金")
    @GetMapping("/getAutoOrderStatus")
    public Result<UserFundDto> getAutoOrderStatus() {
            String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.getUser(userId);

        UserFundDto userFundDto = new UserFundDto();
        // 查余额
        UserFund fund = userFundService.getFund(userId);
        userFundDto.setCqc(fund.getAvailableBalance().multiply(Constants.BUY_ORDER_PERCENT));

        userFundDto.setAutoOrderStatus(user.getAutoOrderStatus());
        userFundDto.setOrderNotify(user.getOrderNotify());

        String newOrderState = ehcacheUtil.get(Constants.NEW_ORDER_PREFIX + user.getId());
        if ("1".equals(newOrderState)) {
            userFundDto.setNewOrderStatus(true);
            ehcacheUtil.remove(Constants.NEW_ORDER_PREFIX + user.getId());
        }

        return Result.success(userFundDto);
    }


    @ApiOperation(value = "更改用户抢单通知")
    @GetMapping("/updateOrderNotify")
    public Result<Boolean> updateOrderNotify(Integer status) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.getUser(userId);

        User entity = new User();
        entity.setId(userId);
        entity.setStatus(status);

        boolean rs = userService.updateById(entity);
        if (!rs) {
            return Result.failed("操作失败");
        }
        return Result.success(true);
    }

}
