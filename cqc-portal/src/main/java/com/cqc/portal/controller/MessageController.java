package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.common.api.PageQuery;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Message;
import com.cqc.portal.service.FaqService;
import com.cqc.portal.service.MessageService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(tags = "消息列表")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @ApiOperation("最近消息列表")
    @GetMapping("/getNew")
    public Result<Page<Message>> getNew(PageQuery param) {

        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        Page<Message> page = new Page<>(param.getPageNum(), param.getPageSize());

        service.page(page, new QueryWrapper<Message>().eq("user_id", userId)
                .orderByDesc("create_time"));

        return Result.success(page);
    }

    @ApiOperation("是否已读")
    @GetMapping("/click")
    public Result<Boolean> click(String messageId) {
        // 将消息改为已读
        Message message = new Message();
        message.setId(messageId);
        message.setStatus(1);

        service.updateById(message);

        return Result.success();
    }

}
