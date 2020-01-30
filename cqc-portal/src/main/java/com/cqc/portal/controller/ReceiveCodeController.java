package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.ReceiveCode;
import com.cqc.model.User;
import com.cqc.portal.dto.ReceiveCodeAddParam;
import com.cqc.portal.service.ReceiveCodeService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
@RestController
@RequestMapping("/receiveCode")
public class ReceiveCodeController {

    @Autowired
    private ReceiveCodeService receiveCodeService;

    @Autowired
    private UserService userService;

    @ApiOperation("我的收款账户")
    @GetMapping("/list")
    public Result<List<ReceiveCode>> list() {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        QueryWrapper<ReceiveCode> wrapper = new QueryWrapper<ReceiveCode>().eq("user_id", userId);
        List<ReceiveCode> list = receiveCodeService.list(wrapper);
        return Result.success(list);
    }


    @ApiOperation("添加收款账号")
    @PostMapping("/add")
    public Result<ReceiveCode> add(@Validated @RequestBody ReceiveCodeAddParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        User user = userService.checkUser(userId);
        // 先判断是否存在该方式是否存在
        ReceiveCode code = receiveCodeService.getCode(userId, param.getType());
        if (code != null) {
            // 报错
            throw new BaseException("", "该方式收款码已经存在");
        }
        ReceiveCode receiveCode = new ReceiveCode();
        receiveCode.setUserId(user.getId());
        receiveCode.setAccount(user.getAccount());

        receiveCode.setReceiveName(param.getReceiveName());
        receiveCode.setChannel(param.getType());
        receiveCode.setCodeImg(param.getCodeImg());

        boolean rs = receiveCodeService.save(receiveCode);
        if (!rs) {
            return Result.failed("添加收款账号失败");
        }
        return Result.success(receiveCode);
    }


    @ApiOperation("启动")
    @GetMapping("/open")
    public Result<Boolean> open(@NotBlank(message = "id不能为空") String id) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        ReceiveCode receiveCode = new ReceiveCode();
        receiveCode.setId(id);
        receiveCode.setStatus(1);

        boolean rs = receiveCodeService.updateById(receiveCode);
        if (!rs) {
            return Result.failed("操作成功");
        }
        return Result.success(true);
    }

    @ApiOperation("关闭")
    @GetMapping("/close")
    public Result<Boolean> close(@NotBlank(message = "id不能为空") String id) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        ReceiveCode receiveCode = new ReceiveCode();
        receiveCode.setId(id);
        receiveCode.setStatus(0);

        boolean rs = receiveCodeService.updateById(receiveCode);
        if (!rs) {
            return Result.failed("操作成功");
        }
        return Result.success(true);
    }

}

