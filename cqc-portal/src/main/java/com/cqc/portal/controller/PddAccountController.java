package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.PddAccount;
import com.cqc.model.User;
import com.cqc.portal.dto.PddAccountAddParam;
import com.cqc.portal.dto.PddAccountQueryParam;
import com.cqc.portal.service.PddAccountService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
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

