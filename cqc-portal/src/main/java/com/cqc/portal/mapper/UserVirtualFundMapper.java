package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.UserVirtualFund;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * 用户-虚拟币资产 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserVirtualFundMapper extends BaseMapper<UserVirtualFund> {

    /**
     * 减去冻结金额
     * @param userId
     * @param type
     * @param amount
     * @return
     */
    @Update("update user_virtual_fund set balance = balance - #{amount}, freeze_balance = freeze_balance = #{amount} where user_id = #{userId} " +
            "and type =1 and freeze_balance >= #{amount} and balance >= #{amount}")
    int cutFreezeBalance(String userId, int type, BigDecimal amount);

}
