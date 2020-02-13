package com.cqc.portal.controller;


import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.portal.dto.WithDrawAddParam;
import com.cqc.portal.service.UserWithdrawService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 提现记录 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/withDraw")
public class UserWithdrawController {

    @Autowired
    private UserWithdrawService service;


    @ApiOperation(value = "提现")
    @PostMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody WithDrawAddParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        boolean b = service.withDraw(userId, param.getBankCardId(), param.getAmount());
        if (!b) {
            return Result.failed("提现失败");
        }
        return Result.success(true);
    }

}

