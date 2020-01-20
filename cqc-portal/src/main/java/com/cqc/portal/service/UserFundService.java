package com.cqc.portal.service;

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
     * 添加金额
     * @param userId
     * @param amount
     * @param type
     * @param remark
     * @return
     */
    boolean addBalance(String userId, BigDecimal amount, int type, String remark);

    /**
     *
     * @param userId
     *
     * @param amount
     * @param type
     * @param remark
     * @return
     */
    boolean cutFreezeBalance(String userId, BigDecimal amount, int type, String remark);

    /**
     * 冻结金额
     * @param userId
     * @param amount
     * @return
     */
    boolean freezeBalance(String userId, BigDecimal amount);
}
