package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserDateIncome;
import com.cqc.portal.dto.resp.UserIncomeDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */
public interface UserDateIncomeService extends IService<UserDateIncome> {

    /**
     * 根据用户 + 日期查询收益
     * @param userId
     * @param date
     * @return
     */
    BigDecimal getIncomeByDate(String userId, String date);

    /**
     * 代理收益统计
     * @param parentUserId
     * @param date
     * @return
     */
    List<UserIncomeDto> getAgentIncome(String parentUserId, String date);

}
