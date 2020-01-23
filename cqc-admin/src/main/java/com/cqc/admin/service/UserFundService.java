package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserFund;

import java.math.BigDecimal;

/**
 * <p>
 * 用户金额 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */
public interface UserFundService extends IService<UserFund> {

    /**
     * 查询用户资金
     * @param userId
     * @return
     */
    UserFund getFund(String userId);

    /**
     * 给用户充值
     * @param userId 用户id
     * @param amount 金额
     * @return
     */
    boolean recharge(String userId, BigDecimal amount);

}
