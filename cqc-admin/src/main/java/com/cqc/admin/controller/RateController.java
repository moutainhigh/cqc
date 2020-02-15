package com.cqc.admin.controller;


import com.cqc.admin.dto.UserSetRateParam;
import com.cqc.admin.service.RateService;
import com.cqc.common.api.Result;
import com.cqc.model.Rate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-24
 */
@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @ApiOperation("费率列表")
    @GetMapping("/list")
    public Result<List<Rate>> getList() {
        List<Rate> list = rateService.list();
        if (!list.contains(new Rate("1"))) {
            list.add(new Rate("1", BigDecimal.ZERO));
        }
        if (!list.contains(new Rate("2"))) {
            list.add(new Rate("2", BigDecimal.ZERO));
        }
        if (!list.contains(new Rate("3"))) {
            list.add(new Rate("3", BigDecimal.ZERO));
        }
        if (!list.contains(new Rate("4"))) {
            list.add(new Rate("4", BigDecimal.ZERO));
        }
        BigDecimal percent = new BigDecimal(100);
        for (Rate rate : list) {
            rate.setRate(rate.getRate().multiply(percent));
        }
        return Result.success(list);
    }

    @ApiOperation("设置费率")
    @PostMapping("/setRate")
    public Result<Boolean> setUserRate(@Validated @RequestBody List<Rate> list) {
        if (!CollectionUtils.isEmpty(list)) {
            BigDecimal percent = new BigDecimal(100);
            for (Rate rate : list) {
                rate.setRate(rate.getRate().divide(percent, 7, RoundingMode.DOWN));
            }
        }
        boolean rs = rateService.saveOrUpdateBatch(list);
        if (!rs) {
            return Result.failed("设置费率失败");
        }
        return Result.success(true);
    }

}

