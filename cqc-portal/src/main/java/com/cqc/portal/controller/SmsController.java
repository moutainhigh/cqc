package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import com.cqc.common.constant.ConstantEnums;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.portal.manager.SmsService;
import com.cqc.portal.service.UserService;
import com.cqc.portal.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author wanglz
 */
@Validated
@RequestMapping("/sms")
@RestController
public class SmsController {

    @Autowired
    private UserService userService;


    @Autowired
    private SmsService service;

    @ApiOperation(value = "发送短信")
    @GetMapping("/send")
    public Result<String> sendRegSms(String mobile, Integer type) {
        // 得先校验手机号码是否存在
        if (!StringUtils.isEmpty(mobile) && userService.checkMobileReg(mobile)) {
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

    @ApiOperation(value = "重置谷歌验证器发送短信")
    @GetMapping("/sendResetGoogle")
    public Result<String> sendResetGoogle(@NotBlank(message = "手机号码不能为空") String mobile) {
        User user = userService.getByMobile(mobile);
        if (null == user) {
            throw new BaseException("", "该手机号码未注册");
        }
        if (StringUtils.isEmpty(user.getGoogleSecret())) {
            throw new BaseException("", "该手机号码未绑定谷歌验证器");
        }
        String tmpId = ConstantEnums.SmsTmpId.REGISTER.getByType(3);

        // 生成验证码
        String code = RandomUtil.generateCode(4);
        try {
            Result<String> result = service.send(tmpId, code, mobile, 3);
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
