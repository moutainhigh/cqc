package com.cqc.admin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserAddParam;
import com.cqc.admin.dto.UserCqcRechargeParam;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.admin.service.UserFundService;
import com.cqc.admin.service.UserService;
import com.cqc.common.api.Result;
import com.cqc.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author wanglz
 * @date 2020-01-08
 **/

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFundService userFundService;


    @ApiOperation("用户列表")
    @GetMapping("/list")
    public Result<Page<UserListDto>> list(UserQueryParam param, BindingResult rs) {

        Page<UserListDto> page = userService.listPage(param);

        return Result.success(page);
    }


    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody UserAddParam param) {
        // 校验两次密码是否一致
        if (!Objects.equals(param.getPassword(), param.getConfirmPassword())) {
            return Result.failed("两次密码不一致");
        }
        boolean rs = userService.addUser(param);
        if (!rs) {
            return Result.failed("添加用户失败");
        }
        return Result.success(true);
    }


    @ApiOperation("封号")
    @GetMapping("/close")
    public Result<Boolean> close(@NotBlank(message = "用户id不能为空") String userId,
                                 @NotBlank(message = "封号时间不能为空") String closeTime) {

        DateTime time = DateUtil.parse(closeTime, "yyyy-MM-dd HH:mm:ss");

        User user = new User();
        user.setId(userId);
        user.setCloseTime(time);
        user.setStatus(2);

        boolean rs = userService.updateById(user);
        if (!rs) {
            return Result.failed("封禁失败");
        }
        return Result.success(true);
    }
    @ApiOperation("解封")
    @GetMapping("/open")
    public Result<Boolean> open(@NotBlank(message = "用户id不能为空") String userId) {
        User user = new User();
        user.setId(userId);
        user.setStatus(1);
        boolean rs = userService.updateById(user);
        if (!rs) {
            return Result.failed("解封失败");
        }
        return Result.success(true);
    }


    @ApiOperation("删除")
    @GetMapping("/delete")
    public Result<Boolean> delete(@NotBlank(message = "用户id不能为空") String id) {
        User user = new User();
        user.setId(id);
        user.setStatus(-1);

        boolean rs = userService.updateById(user);
        if (!rs) {
            return Result.failed("删除失败");
        }
        return Result.success(true);
    }



    @ApiOperation("充值")
    @PostMapping("/recharge")
    public Result<Boolean> recharge(@Validated @RequestBody UserCqcRechargeParam param) {
        log.info("充值参数, {}", param);

        boolean rs = userFundService.recharge(param.getUserId(), param.getCqc());
        if (!rs) {
            return Result.failed("充值失败");
        }
        return Result.success();
    }



}
