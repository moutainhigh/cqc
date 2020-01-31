package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.portal.dto.GoogleSecretBindParam;
import com.cqc.portal.dto.resp.GoogleSecret;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.GoogleAuthUtil;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "谷歌验器")
@RequestMapping("/googleAuth")
@RestController
public class GoogleAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/secret")
    private Result<GoogleSecret> genSecret() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        if (!StringUtils.isEmpty(user.getGoogleSecret())) {
            // 已经绑定google验证器
            return Result.failed("已经绑定谷歌验证器");
        }
        String account = PortalUserUtil.getCurrentUsername();
        String secret = GoogleAuthUtil.getSecret();
        String qrCode = GoogleAuthUtil.getQrcode(account, secret);

        GoogleSecret vo = new GoogleSecret(qrCode, secret);

        return Result.success(vo);
    }

    @PostMapping("/bind")
    public Result<Boolean> bindGoogle(@Validated @RequestBody GoogleSecretBindParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        // 先校验密钥与code是否一致
        long t = System.currentTimeMillis();
        boolean b = GoogleAuthUtil.checkCode(param.getCode(), param.getSecret(), t);
        log.info("谷歌验证， 账号 : {}, 密钥: {}, code ： {}", userId, param.getSecret(), param.getCode());
        if (!b) {
            return Result.failed("验证码错误，绑定失败");
        }
        User user = new User();
        user.setId(userId);
        user.setGoogleSecret(param.getSecret());
        boolean rs = userService.updateById(user);
        if (!rs) {
            return Result.failed("绑定失败");
        }
        return Result.success();
    }
}
