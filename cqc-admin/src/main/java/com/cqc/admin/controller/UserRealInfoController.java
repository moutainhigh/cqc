package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserRealInfoQueryParam;
import com.cqc.admin.dto.resp.UserRealInfoDto;
import com.cqc.admin.service.UserRealInfoService;
import com.cqc.admin.service.UserService;
import com.cqc.common.api.Result;
import com.cqc.model.User;
import com.cqc.model.UserRealInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户实名信息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Api(tags = "用户实名认证管理")
@RestController
@RequestMapping("/userRealInfo")
public class UserRealInfoController {

    @Autowired
    private UserRealInfoService service;

    @Autowired
    private UserService userService;

    @ApiOperation("列表")
    @GetMapping("/list")
    public Result<Page<UserRealInfoDto>> listPage(UserRealInfoQueryParam param) {
        Page<UserRealInfoDto> page = service.listPage(param);
        return Result.success(page);
    }

    @ApiOperation("列表")
    @GetMapping("/detail")
    public Result<UserRealInfoDto> detail(@NotBlank(message = "id不能为空") String id) {
        UserRealInfoDto detail = new UserRealInfoDto();
        UserRealInfo byId = service.getById(id);
        if (byId != null) {
            BeanUtils.copyProperties(byId, detail);
            // 查用户
            User user = userService.getById(byId.getUserId());
            if (user != null) {
                detail.setAccount(user.getAccount());
            }
        }
        return Result.success(detail);
    }


    @ApiOperation("通过/或者不通过")
    @GetMapping("/audit")
    public Result<Boolean> audit(@NotBlank(message = "id不能为空") String id,
                                 @NotNull(message = "status不能为空") Integer status) {

        UserRealInfo entity = new UserRealInfo();
        entity.setId(id);
        entity.setStatus(status);

        boolean rs = service.updateById(entity);
        if (!rs) {
            return Result.failed("操作失败");
        }
        return Result.success(true);
    }

}

