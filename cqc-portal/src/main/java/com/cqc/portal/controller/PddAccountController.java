package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.PddAccount;
import com.cqc.model.User;
import com.cqc.portal.dto.PddAccountAddParam;
import com.cqc.portal.dto.PddAccountQueryParam;
import com.cqc.portal.service.PddAccountService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.GoogleAuthUtil;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-02-10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/pddAccount")
public class PddAccountController {

    @Autowired
    private PddAccountService service;

    @Autowired
    private UserService userService;

    @ApiOperation("我的平多多账户")
    @GetMapping("/list")
    public Result<List<PddAccount>> list(PddAccountQueryParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        param.setUserId(userId);

        QueryWrapper<PddAccount> wrapper = new QueryWrapper<PddAccount>()
                .eq("user_id", param.getUserId())
                .eq("type", param.getType()).orderByDesc("create_time");
        List<PddAccount> list = service.list(wrapper);
        return Result.success(list);
    }


    @ApiOperation("添加拼多多账号")
    @PostMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody PddAccountAddParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        // 判断用户是否绑定了谷歌验证码
        if (!StringUtils.isEmpty(user.getGoogleSecret())) {
            // 开启了谷歌验证码，但是没有输入验证码，报错
            if (StringUtils.isEmpty(param.getGoogleCode())) {
                throw new BaseException(BaseErrorMsg.NO_GOOGLE_CODE);
            }
            // 此时验证码谷歌验证码
            long t = System.currentTimeMillis();
            boolean b = GoogleAuthUtil.checkCode(param.getGoogleCode(), user.getGoogleSecret(), t);
            log.info("谷歌验证， 账号 : {}, 密钥: {}, code ： {}", user.getId(), user.getGoogleSecret(), param.getGoogleCode());
            if (!b) {
                throw new BaseException(BaseErrorMsg.GOOGLE_CODE_ERROR);
            }
        }
        PddAccount pddAccount = new PddAccount();
        pddAccount.setUserId(userId);
        pddAccount.setAccount(user.getAccount());
        pddAccount.setType(param.getType());
        pddAccount.setPddAccount(param.getPddAccount());
        pddAccount.setPassword(param.getPassword());

        boolean rs = service.save(pddAccount);
        if (!rs) {
            return Result.failed("添加拼多多账号失败");
        }
        return Result.success();
    }
}

