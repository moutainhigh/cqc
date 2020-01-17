package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.Result;
import com.cqc.model.Faq;
import com.cqc.portal.service.FaqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 常见问题 前端控制器
 * </p>
 *
 * @author wanglz
 * @since 2020-01-16
 */
@Api(tags = "FAQ问题")
@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public Result<List<Faq>> faqList() {
        List<Faq> list = faqService.list(new QueryWrapper<Faq>().eq("status", 1)
                .orderByAsc("id"));
        return Result.success(list);
    }


}
