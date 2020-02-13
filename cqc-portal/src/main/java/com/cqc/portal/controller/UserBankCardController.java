package com.cqc.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.api.Result;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.Bank;
import com.cqc.model.UserBankCard;
import com.cqc.portal.dto.BankCardAddParam;
import com.cqc.portal.service.BankService;
import com.cqc.portal.service.UserBankCardService;
import com.cqc.portal.service.UserService;
import com.cqc.security.util.PortalUserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 用户-银行卡 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/bankCard")
@Validated
public class UserBankCardController {

    @Autowired
    private BankService bankService;

    @Autowired
    private UserBankCardService service;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "添加银行卡")
    @PostMapping("/add")
    public Result<Boolean> add(@Validated @RequestBody BankCardAddParam param) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        Bank bank = bankService.getById(param.getBankId());
        if (bank == null) {
            return Result.failed("银行错误");
        }
        UserBankCard card = new UserBankCard();
        card.setUserId(userId);
        card.setCardNo(param.getCardNo());
        card.setCardType(1);
        card.setBankName(bank.getName());
        card.setBankLogo(bank.getLogo());
        boolean rs = service.save(card);
        if (!rs) {
            return Result.failed("添加银行卡失败");
        }
        return Result.success();
    }


    @ApiOperation(value = "我的银行卡")
    @GetMapping("/myCardList")
    public Result<List<UserBankCard>> myCardList(){
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        List<UserBankCard> list = service.list(new QueryWrapper<UserBankCard>().eq("user_id", userId)
        .orderByDesc("create_time"));
        if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
            list.get(0).setIsDefault(true);
        }
        // 加*
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach( card -> {
                String cardNo = card.getCardNo();
                if (!StringUtils.isEmpty(cardNo)) {
                    if (cardNo.length() >= 4) {
                        card.setCardNo("**** " + cardNo.substring(cardNo.length() - 4));
                    } else {
                        card.setCardNo("**** " + cardNo);
                    }
                }
            });
        }
        return Result.success(list);
    }

    @ApiOperation(value = "设为默认")
    @GetMapping("/setDefault")
    public Result<Boolean> setDefault(@NotBlank(message = "id不能为空") String id){
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        // 先设置所有为0
        UserBankCard card = new UserBankCard();
        card.setIsDefault(false);
        service.update(card, new QueryWrapper<UserBankCard>().eq("user_id", userId));

        UserBankCard entity = new UserBankCard();
        entity.setId(id);
        entity.setIsDefault(true);
        boolean rs = service.updateById(entity);
        if (!rs) {
            return Result.failed("设置成功");
        }
        return Result.success();
    }

    @ApiOperation(value = "删除银行卡")
    @GetMapping("/remove")
    public Result<Boolean> remove(@NotBlank(message = "id不能为空") String id) {
        String userId = PortalUserUtil.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        userService.checkUser(userId);
        boolean rs = service.removeById(id);
        if (!rs) {
            return Result.failed("删除");
        }
        return Result.success();
    }



}

