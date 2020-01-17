package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.admin.dao.NoticeAddParam;
import com.cqc.admin.dao.NoticeEditParam;
import com.cqc.admin.service.NoticeService;
import com.cqc.common.api.Result;
import com.cqc.model.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 公告 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@Api(tags = "公告管理")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("公告列表")
    @GetMapping("/list")
    public Result<List<Notice>> list(String type){

        List<Notice> list = noticeService.list(new QueryWrapper<Notice>().
                eq(StringUtils.isEmpty(type), "type", type)
                .eq("status", 1));
        return Result.success(list);
    }


    @ApiOperation("添加公告")
    @GetMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody NoticeAddParam param) {

        Notice notice = new Notice();
        notice.setTitle(param.getTitle());
        notice.setContent(param.getContent());
        notice.setType(param.getType());
        notice.setStatus(1);
        notice.setAuthor("admin");

        boolean rs = noticeService.save(notice);
        if (!rs) {
            return Result.failed("发布成功");
        }
        return Result.success(true);
    }

    @ApiOperation("添加公告")
    @GetMapping("/edit")
    public Result<Boolean> edit(@Validated @RequestBody NoticeEditParam param) {
        if (param.isEmpty()) {
            return Result.success();
        }
        Notice notice = new Notice();
        notice.setTitle(param.getTitle());
        notice.setContent(param.getContent());
        notice.setId(param.getId());
        boolean rs = noticeService.updateById(notice);
        if (!rs) {
            return Result.failed("编辑成功");
        }
        return Result.success(true);
    }


}

