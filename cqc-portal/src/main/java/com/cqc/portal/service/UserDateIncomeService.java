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
     * @param userIds
     * @param date
     * @return
     */
    List<UserDateIncome> getAgentIncome(List<String> userIds, String date);

    /**
     * 更新用户每日收益
     * @param entity
     * @return
     */
    boolean updateUserIncome(UserDateIncome entity);

    /**
     * 保存上级收益
     * @param userId 用户id
     * @param date 日期
     * @return
     */
    boolean saveUserIncome(String userId, BigDecimal amount, String date);
}
