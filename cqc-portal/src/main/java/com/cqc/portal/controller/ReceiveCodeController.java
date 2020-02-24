package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import com.cqc.model.ReceiveCode;
import com.cqc.model.User;
import com.cqc.model.UserRealInfo;
import com.cqc.portal.dto.ReceiveCodeAddParam;
import com.cqc.portal.dto.ReceiveCodeQueryParam;
import com.cqc.portal.service.ReceiveCodeService;
import com.cqc.portal.service.UserRealInfoService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.GoogleAuthUtil;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Validated
@RestController
@RequestMapping("/receiveCode")
public class ReceiveCodeController {

    @Autowired
    private ReceiveCodeService receiveCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRealInfoService userRealInfoService;

    @ApiOperation("我的收款账户")
    @GetMapping("/list")
    public Result<List<ReceiveCode>> list(ReceiveCodeQueryParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        QueryWrapper<ReceiveCode> wrapper = new QueryWrapper<ReceiveCode>()
                .eq("user_id", userId)
                .like(!StringUtils.isEmpty(param.getName()), "receive_name", param.getName())
                .eq(param.getType() != null, "channel", param.getType());
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
        // 查实名信息
        UserRealInfo realInfo = userRealInfoService.getRealInfo(userId);
        if (realInfo == null) {
            // 如果没实名 不允许抢单
            throw new BaseException(BaseErrorMsg.NOT_REAL);
        }

        User user = userService.checkUser(userId);
        if (StringUtils.isEmpty(user.getGoogleSecret())) {
            throw new BaseException(BaseErrorMsg.NO_BIND_GOODS_SECRET);
        }
        // 此时验证码谷歌验证码
        long t = System.currentTimeMillis();
        boolean b = GoogleAuthUtil.checkCode(param.getGoogleCode(), user.getGoogleSecret(), t);
        log.info("谷歌验证， 账号 : {}, 密钥: {}, code ： {}", user.getId(), user.getGoogleSecret(), param.getGoogleCode());
        if (!b) {
            throw new BaseException(BaseErrorMsg.GOOGLE_CODE_ERROR);
        }
        // 判断用户是否绑定了谷歌验证码
        /*if (!StringUtils.isEmpty(user.getGoogleSecret())) {

        }*/
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

    @ApiOperation("删除")
    @GetMapping("/delete")
    public Result<Boolean> delete(@NotBlank(message = "id不能为空") String id) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);


        boolean rs = receiveCodeService.removeById(id);
        if (!rs) {
            return Result.failed("操作成功");
        }
        return Result.success(true);
    }

}

