package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.BankAddParam;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.admin.service.BankService;
import com.cqc.common.api.Result;
import com.cqc.model.Bank;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 银行 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Validated
@Api(tags = "银行数量管理")
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @ApiOperation("银行列表")
    @GetMapping("/list")
    public Result<List<Bank>> list() {
        List<Bank> list = bankService.list(new QueryWrapper<Bank>().orderByDesc("create_time"));
        return Result.success(list);
    }


    @ApiOperation("添加银行")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @Validated BankAddParam param) {
        Bank bank = new Bank(param.getName(), param.getLogo());
        boolean rs = bankService.save(bank);
        if (!rs) {
            return Result.failed("添加银行失败");
        }
        return Result.success(true);
    }


}

