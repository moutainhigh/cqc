package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.UserDateIncome;
import com.cqc.portal.dto.resp.UserIncomeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */
public interface UserDateIncomeMapper extends BaseMapper<UserDateIncome> {

    /**
     * 查询用户下代理收益
     * @param userIds
     * @param date
     * @return
     */
    List<UserDateIncome> queryAgentIncome(@Param("userIds") List<String> userIds, String date);

    int updateIncome(UserDateIncome entity);
}
