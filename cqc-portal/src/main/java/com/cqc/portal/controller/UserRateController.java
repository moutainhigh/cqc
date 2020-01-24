package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserRate;
import com.cqc.portal.service.UserRateService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 用户费率 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Api(tags = "用户费率")
@RestController
@RequestMapping("/userRate")
public class UserRateController {

    @Autowired
    private UserRateService userRateService;

    @Autowired
    private UserService userService;

    @ApiOperation("用户费率列表")
    @GetMapping("/list")
    public Result<List<UserRate>> listByUserId() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        List<UserRate> list = userRateService.list(new QueryWrapper<UserRate>().eq("user_id", userId));
        return Result.success(list);
    }




}

