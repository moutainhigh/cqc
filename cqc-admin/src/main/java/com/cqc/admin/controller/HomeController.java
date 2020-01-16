package com.cqc.admin.controller;

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

    @GetMapping("")
    public String index() {
        return "hello welcome cqc !";
    }

}
