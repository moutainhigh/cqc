package com.cqc.portal.controller;


import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UmsAdmin;
import com.cqc.model.UserRealInfo;
import com.cqc.portal.dto.UserRealInfoAddParam;
import com.cqc.portal.service.UserRealInfoService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户实名信息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Api(tags = "用户实名认证")
@RestController
@RequestMapping("/realInfo")
@Validated
public class UserRealInfoController {

    @Autowired
    private UserRealInfoService service;

    @ApiOperation(value = "提交实名信息")
    @GetMapping("/get")
    public Result<UserRealInfo> get() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        UserRealInfo realInfo = service.getRealInfo(userId);
        if (realInfo == null) {
            return Result.failed("暂未实名");
        }
        return Result.success(realInfo);
    }

    @ApiOperation(value = "提交实名信息")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Result<Boolean> apply(@RequestBody UserRealInfoAddParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        param.setUserId(userId);
        boolean rs = service.apply(param);
        if (!rs) {
            return Result.failed("提交实名信息失败");
        }
        return Result.success();
    }


}

