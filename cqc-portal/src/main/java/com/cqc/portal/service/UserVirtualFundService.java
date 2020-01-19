package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserVirtualFund;

import java.math.BigDecimal;

/**
 * <p>
 * 用户-虚拟币资产 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserVirtualFundService extends IService<UserVirtualFund> {

    /**
     *
     * @param userId
     * @param type
     * @param amount
     * @param remark
     * @return
     */
    boolean cutFreezeBalance(String userId, int type, BigDecimal amount, String remark);


}
