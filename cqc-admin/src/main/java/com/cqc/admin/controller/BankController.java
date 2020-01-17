package com.cqc.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.admin.service.BankService;
import com.cqc.common.api.Result;
import com.cqc.model.Bank;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 银行 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Api(tags = "银行数量管理")
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @ApiOperation("银行列表")
    @GetMapping("/list")
    public Result<List<Bank>> list() {
        List<Bank> list = bankService.list();
        return Result.success(list);
    }


    @ApiOperation("添加银行")
    @GetMapping("/add")
    public Result<Boolean> add(String name, String logo) {
        Bank bank = new Bank(name, logo);
        boolean rs = bankService.save(bank);
        if (!rs) {
            return Result.failed("添加银行失败");
        }
        return Result.success(true);
    }


}

