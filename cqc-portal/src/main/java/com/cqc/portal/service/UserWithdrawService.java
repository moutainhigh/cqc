package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserWithdraw;

import java.math.BigDecimal;

/**
 * <p>
 * 提现记录 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
public interface UserWithdrawService extends IService<UserWithdraw> {

    /**
     * 提现
     * @param userId
     * @param bankCardId
     * @param amount
     * @return
     */
    boolean withDraw(String userId, String bankCardId, BigDecimal amount);

}
