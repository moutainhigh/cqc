package com.cqc.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserBankCardQueryParam;
import com.cqc.admin.dto.resp.UserBankCardListDto;
import com.cqc.admin.service.UserBankCardService;
import com.cqc.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户-银行卡 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/bankCard")
@Validated
public class UserBankCardController {

    @Autowired
    private UserBankCardService service;

    @ApiOperation("银行卡列表")
    @GetMapping("/list")
    public Result<Page<UserBankCardListDto>> list(UserBankCardQueryParam param){
        Page<UserBankCardListDto> pageList = service.pageList(param);

        return Result.success(pageList);
    }


    //@ApiOperation(value = "冻结")
    //@GetMapping("/remove")
    public Result<Boolean> remove(@NotBlank(message = "id不能为空") String id) {

        boolean rs = service.removeById(id);
        if (!rs) {
            return Result.failed("删除");
        }
        return Result.success();
    }



}

