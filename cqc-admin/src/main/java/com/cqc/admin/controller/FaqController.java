package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.admin.dao.FaqAddParam;
import com.cqc.admin.service.FaqService;
import com.cqc.common.api.Result;
import com.cqc.model.Faq;
import com.cqc.model.Notice;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 常见问题 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @ApiOperation("常见问题")
    @GetMapping("/list")
    public Result<List<Faq>> list(){
        List<Faq> list = faqService.list(new QueryWrapper<Faq>().eq("status", 1));
        return Result.success(list);
    }

    

    @ApiOperation("添加常见问题")
    @GetMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody FaqAddParam param){
        Faq faq = new Faq();
        BeanUtils.copyProperties(param, faq);
        boolean rs = faqService.save(faq);
        if (!rs) {
            return Result.failed("添加失败");
        }
        return Result.success(true);
    }


}

