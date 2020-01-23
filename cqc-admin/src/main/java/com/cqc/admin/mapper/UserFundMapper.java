package com.cqc.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.UserFund;

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


    int addBalance(String userId, BigDecimal amount);

}
