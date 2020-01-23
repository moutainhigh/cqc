package com.cqc.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.admin.dto.resp.HomeDataDto;
import com.cqc.admin.service.OrderService;
import com.cqc.admin.service.UserService;
import com.cqc.common.api.Result;
import com.cqc.model.Order;
import com.cqc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public String index() {
        return "hello welcome cqc !";
    }

    @GetMapping("/homeData")
    public Result<HomeDataDto> homeData() {

        Integer count = userService.count(new QueryWrapper<User>().ne("status", -1));

        Integer orderTotal = orderService.count();

        HomeDataDto dataDto = new HomeDataDto();
        dataDto.setUserTotal(count);
        dataDto.setOrderTotal(orderTotal);

        return Result.success(dataDto);
    }


}
