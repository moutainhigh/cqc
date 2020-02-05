package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.Result;
import com.cqc.model.Notice;
import com.cqc.portal.service.FaqService;
import com.cqc.portal.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 常见问题 前端控制器
 * </p>
 *
 * @author wanglz
 * @since 2020-01-16
 */

@Api(tags = "公告API")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("获取最新公告")
    @GetMapping("/list")
    public Result<List<Notice>> noticeList(@NotNull(message="type不能为空")Integer type) {

        List<Notice> list = noticeService.list(new QueryWrapper<Notice>().
                eq(!StringUtils.isEmpty(type), "type", type)
                .eq("status", 1).orderByDesc("create_time"));
        return Result.success(list);
    }
}
