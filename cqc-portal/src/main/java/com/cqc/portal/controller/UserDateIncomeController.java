package com.cqc.portal.controller;


import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Bank;
import com.cqc.portal.dto.resp.UserIncomeDto;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */
@RestController
@RequestMapping("/income")
public class UserDateIncomeController {

    @Autowired
    private UserDateIncomeService service;

    @ApiOperation("银行列表")
    @GetMapping("/agentIncome")
    public Result<List<UserIncomeDto>> list(String date) {
        // 校验日期格式
        if (!StringUtils.isEmpty(date) && date.length() != 8) {
            return Result.failed("错误的日期");
        }
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        List<UserIncomeDto> dataList = service.getAgentIncome(userId, date);
        return Result.success(dataList);
    }

}

