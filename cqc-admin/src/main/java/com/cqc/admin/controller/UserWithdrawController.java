package com.cqc.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.WithDrawQueryParam;
import com.cqc.admin.dto.resp.UserWithDrawDto;
import com.cqc.admin.service.UserWithdrawService;
import com.cqc.common.api.Result;
import com.cqc.model.UserWithdraw;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 提现记录 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */

@Slf4j
@Api(tags = "提现管理")
@RestController
@RequestMapping("/withDraw")
public class UserWithdrawController {

    @Autowired
    private UserWithdrawService service;

    @ApiOperation("提现列表")
    @GetMapping("/list")
    public Result<Page<UserWithDrawDto>> pageList(WithDrawQueryParam param) {
        Page<UserWithDrawDto> page = service.listPage(param);
        return Result.success(page);
    }

    @ApiOperation("提现退回")
    @GetMapping("/back")
    public Result<Boolean> back(@NotBlank(message = "id不能为空") String id) {
        log.info("提现退回 :{}", id);
        service.back(id);
        return Result.success();
    }


    @ApiOperation("提现确认")
    @GetMapping("/confirm")
    public Result<Boolean> confirm(@NotBlank(message = "id不能为空")String id) {
        log.info("提现确认 :{}", id);
        UserWithdraw withdraw = service.getById(id);
        if (withdraw == null) {
            return Result.failed("失败");
        }
        if (withdraw.getStatus() != 0) {
            return Result.failed("失败");
        }
        UserWithdraw entity = new UserWithdraw();
        entity.setId(id);
        entity.setStatus(1);

        boolean b = service.updateById(entity);
        if (!b) {
            return Result.failed("操作失败");
        }
        return Result.success();
    }




}

