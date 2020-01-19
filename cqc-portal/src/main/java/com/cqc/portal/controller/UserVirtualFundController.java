package com.cqc.portal.controller;


import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.portal.service.UserVirtualFundService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-虚拟币资产 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/userVirtualFund")
public class UserVirtualFundController {

    @Autowired
    private UserVirtualFundService service;

    @ApiOperation("缴纳做单押金")
    @GetMapping("/payDeposit")
    public Result<Boolean> payDeposit(){
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }





        return Result.success();

    }

}

