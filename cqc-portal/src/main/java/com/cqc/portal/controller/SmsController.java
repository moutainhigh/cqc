package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import com.cqc.common.constant.ConstantEnums;
import com.cqc.common.exception.BaseException;
import com.cqc.portal.manager.SmsService;
import com.cqc.portal.service.UserService;
import com.cqc.portal.utils.EhcacheUtil;
import com.cqc.portal.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RequestMapping("/sms")
@RestController
public class SmsController {

    @Autowired
    private UserService userService;

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Autowired
    private SmsService service;

    @ApiOperation(value = "发送短信")
    @GetMapping("/send")
    public Result<String> sendRegSms(String mobile, Integer type) {
        // 得先校验手机号码是否存在
        if (!StringUtils.isEmpty(mobile) && !userService.checkMobileReg(mobile)) {
            throw new BaseException("", "该手机号码已被注册");
        }
        String tmpId = ConstantEnums.SmsTmpId.REGISTER.getByType(type);
        // 生成验证码
        String code = RandomUtil.generateCode(4);
        try {
            Result<String> result = service.send(tmpId, code, mobile, 1);
            if (result.hashError()) {
                return result;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failed();
    }



}
