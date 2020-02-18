package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Bank;
import com.cqc.model.User;
import com.cqc.model.UserDateIncome;
import com.cqc.portal.dto.resp.UserIncomeDto;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private UserService userService;

    @ApiOperation("代理列表")
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
        userService.checkUser(userId);
        //先查询下级
        List<User> list = userService.getByParent(userId);
        if (CollectionUtils.isEmpty(list)) {
            return Result.success();
        }
        List<UserIncomeDto> resultList = new ArrayList<>();

        List<String> userIds = new ArrayList<>(list.size());
        list.stream().forEach(item -> {
            userIds.add(item.getId());
            resultList.add(new UserIncomeDto(item.getId(), item.getAccount(), date));
        });
        // 从收益表里查询
        List<UserDateIncome> incomeList = service.getAgentIncome(userIds, date);

        if (CollectionUtils.isEmpty(incomeList)) {
            return Result.success(resultList);
        }
        for (UserIncomeDto incomeDto : resultList) {
            for (UserDateIncome income : incomeList) {
                if (incomeDto.getUserId().equals(income.getUserId())) {
                    incomeDto.setIncome(income.getIncome());
                    incomeDto.setTeamIncome(income.getTeamIncome());
                }
            }
        }
        return Result.success(resultList);
    }


    @ApiOperation("下级列表")
    @GetMapping("/child")
    public Result<List<UserIncomeDto>> childList(String childId) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        User child = userService.getById(childId);
        if (child == null || !Objects.equals(userId, child.getRefUserId())) {
            return Result.failed("错误的用户id");
        }
        //先查询下级
        List<User> list = userService.getByParent(childId);
        if (CollectionUtils.isEmpty(list)) {
            return Result.success();
        }
        List<UserIncomeDto> resultList = new ArrayList<>();

        List<String> userIds = new ArrayList<>(list.size());
        list.stream().forEach(item -> {
            userIds.add(item.getId());
            resultList.add(new UserIncomeDto(item.getId(), item.getAccount(), item.getCreateTime()));
        });

        return Result.success(resultList);
    }


    @ApiOperation("上级会员")
    @GetMapping("/parent")
    public Result<UserIncomeDto> getParent(String childId) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);

        User child = userService.getById(childId);
        if (child == null ) {
            return Result.failed("错误的用户id");
        }
        User parent = userService.getById(child.getRefUserId());
        if (parent == null) {
            return Result.success();
        }
        UserIncomeDto result = new UserIncomeDto(parent.getId(), parent.getAccount(), parent.getCreateTime());
        return Result.success(result);
    }

}

