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
     * 加佣金
     * @param userId
     * @param amount
     * @return
     */
    boolean addIncome(String userId, BigDecimal amount);

    /**
     * 保存推荐收益
     * @param userId
     * @param amount
     * @return
     */
    boolean addRefIncome(String userId, BigDecimal amount);
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
     * 减少冻结金额
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

    /**
     * 提现
     * @param userId
     * @param amount
     * @return
     */
    boolean withDraw(String userId, BigDecimal amount);
}
