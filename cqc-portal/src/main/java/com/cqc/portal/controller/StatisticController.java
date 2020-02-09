package com.cqc.portal.controller;

import com.cqc.common.api.Result;
import com.cqc.portal.dto.resp.CityHotDataDto;
import com.cqc.portal.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/statistic")
@RestController
public class StatisticController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "城市热度榜")
    @GetMapping("/cityHot")
    public Result<List<CityHotDataDto>> cityHot() {
        List<CityHotDataDto> list = orderService.cityHot();
        return Result.success(list);
    }

}
