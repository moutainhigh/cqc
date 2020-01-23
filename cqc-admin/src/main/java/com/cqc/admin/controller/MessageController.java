package com.cqc.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dao.NoticeAddParam;
import com.cqc.admin.dto.MessageAddParam;
import com.cqc.admin.dto.MessageQueryParam;
import com.cqc.admin.service.MessageService;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.model.Message;
import com.cqc.model.Notice;
import com.cqc.model.Order;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 消息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @ApiOperation("消息列表")
    @GetMapping("/list")
    public Result<Page<Message>> list(MessageQueryParam param){
        Page<Message> page = new Page<>(param.getPageNum(), param.getPageSize());

        service.page(page, new QueryWrapper<Message>().
                eq(!StringUtils.isEmpty(param.getType()), "type", param.getType())
                .eq("status", 1));
        return Result.success(page);
    }

    @ApiOperation("添加消息")
    @PostMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody MessageAddParam param) {

        Message message = new Message();
        message.setTitle(param.getTitle());
        message.setContent(param.getContent());
        message.setType(param.getType());
        message.setStatus(1);
        message.setAuthor("admin");

        boolean rs = service.save(message);
        if (!rs) {
            return Result.failed("发布成功");
        }
        return Result.success(true);
    }

}

