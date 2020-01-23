package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.admin.dto.UserSetRateParam;
import com.cqc.admin.service.UserRateService;
import com.cqc.common.api.Result;
import com.cqc.model.UserRate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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


    @ApiOperation("用户费率列表")
    @GetMapping("/list")
    public Result<List<UserRate>> listByUserId(@NotBlank(message = "userId不能为空") String userId) {
        List<UserRate> list = userRateService.list(new QueryWrapper<UserRate>().eq("user_id", userId));
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }
        return Result.success(list);
    }


    @ApiOperation("设置费率")
    @PostMapping("/setUserRate")
    public Result<Boolean> setUserRate(@Validated @RequestBody UserSetRateParam param) {

        boolean rs = userRateService.setUserRate(param.getUserId(), param.getRateList());
        if (!rs) {
            return Result.failed("设置费率失败");
        }
        return Result.success(true);
    }

}

