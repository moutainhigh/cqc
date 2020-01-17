package com.cqc.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.InviteCode;
import com.cqc.portal.service.InviteCodeService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Api(tags = "邀请码API")
@RestController
@RequestMapping("/inviteCode")
public class InviteCodeController {

    @Autowired
    private InviteCodeService inviteCodeService;

    @ApiOperation("获取邀请码")
    @GetMapping("/get")
    public Result<String> getInviteCode() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        InviteCode inviteCode = inviteCodeService.getOne(new QueryWrapper<InviteCode>().eq("user_id", userId)
                .gt("expire", timestamp));
        if (inviteCode == null) {
            return Result.failed("没有邀请码");
        }
        return Result.success(inviteCode.getInviteCode());
    }

    @ApiOperation("获取邀请码")
    @GetMapping("/create")
    public Result<String> genInviteCode() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        // 创建邀请码
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        int len = 8;
        StringBuilder sb = new StringBuilder("");
        Random rd = new Random();
        for (int i = 0; i < len; i++) {
            int nextInt = rd.nextInt(35);
            sb.append(base.charAt(nextInt));
        }

        int expire = (int) (System.currentTimeMillis() / 1000) + 600;
        // 保存到数据库
        InviteCode inviteCode = new InviteCode();
        inviteCode.setUserId(userId);
        inviteCode.setInviteCode(sb.toString());
        inviteCode.setExpire(expire);

        boolean rs = inviteCodeService.save(inviteCode);
        if (!rs) {
            return Result.failed("生成邀请码失败");
        }
        return Result.success(inviteCode.getInviteCode());
    }
}
