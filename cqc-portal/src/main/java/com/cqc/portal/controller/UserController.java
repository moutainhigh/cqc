package com.cqc.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Message;
import com.cqc.model.UserRealInfo;
import com.cqc.model.UserVirtualFund;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import com.cqc.portal.dto.UserRealInfoAddParam;
import com.cqc.portal.dto.resp.UserInfo;
import com.cqc.portal.service.UserRealInfoService;
import com.cqc.portal.service.UserService;
import com.cqc.portal.service.UserVirtualFundService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Api(tags = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRealInfoService userRealInfoService;

    @Autowired
    private UserVirtualFundService userVirtualFundService;

    @ApiOperation("登录用户数据")
    @GetMapping("/getInfo")
    public Result<UserInfo> getInfo() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(PortalUserUtil.getCurrentUser().getAccount());
        // 查实名信息
        UserRealInfo realInfo = userRealInfoService.getRealInfo(userId);
        if (realInfo != null) {
            userInfo.setReal(1);
            userInfo.setRealName(realInfo.getRealName());
        }
        // 查余额
        UserVirtualFund fund = userVirtualFundService.getOne(new QueryWrapper<UserVirtualFund>().eq("user_id", userId)
                .eq("type", 1));
        if (fund != null) {
            userInfo.setCqc(fund.getAvailableBalance());
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
        boolean rs = userService.modifyPayPwd(userId, param);
        if (!rs) {
            return Result.failed("修改支付密码失败");
        }
        return Result.success();
    }


    @ApiOperation(value = "修改地区")
    @RequestMapping(value = "/modifyArea", method = RequestMethod.POST)
    public Result<Boolean> modifyArea(@RequestBody ModifyAreaParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        boolean rs = userService.modifyArea(userId, param);
        if (!rs) {
            return Result.failed("修改地区失败");
        }
        return Result.success();
    }

}
