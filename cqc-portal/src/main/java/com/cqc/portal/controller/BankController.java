package com.cqc.portal.controller;


import com.cqc.common.api.Result;
import com.cqc.model.Bank;
import com.cqc.portal.service.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

