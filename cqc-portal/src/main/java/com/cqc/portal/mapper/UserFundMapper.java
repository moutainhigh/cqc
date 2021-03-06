package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.UserFund;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * 用户金额 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */
public interface UserFundMapper extends BaseMapper<UserFund> {


    int addCommission(String userId, BigDecimal amount);


    int cutBalance(String userId, BigDecimal amount);

    int addBalance(String userId, BigDecimal amount);

    int cutFreezeBalance(String userId, BigDecimal amount);

    int freezeBalance(String userId, BigDecimal amount);

    /**
     * 解冻金额
     * @param userId
     * @param amount
     * @return
     */
    int unFreezeBalance(String userId, BigDecimal amount);


    int cutBalance4PddSeller(String userId, BigDecimal amount);

    int cutBalance4PddBuyer(String userId, BigDecimal amount);
}
