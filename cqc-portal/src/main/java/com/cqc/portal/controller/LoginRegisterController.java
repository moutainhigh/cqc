package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import com.cqc.model.UmsAdmin;
import com.cqc.model.User;
import com.cqc.portal.dto.LoginParam;
import com.cqc.portal.dto.RegisterParam;
import com.cqc.portal.service.LoginRegisterService;
import com.cqc.security.bean.AccessToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Api(tags = "登录-注册")
@RestController
@Validated
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService service;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> register(@Validated @RequestBody RegisterParam param) {
        if (Objects.equals(param.getPassword(), param.getConfirmPassword())) {
            return Result.failed("两次密码不一致");
        }
        User user = service.register(param);
        if (user == null) {
            return Result.failed();
        }
        return Result.success();
    }


    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<AccessToken> login(@Validated @RequestBody LoginParam param) {

        AccessToken login = service.login(param.getUsername(), param.getPassword());
        return Result.success(login);
    }


}
