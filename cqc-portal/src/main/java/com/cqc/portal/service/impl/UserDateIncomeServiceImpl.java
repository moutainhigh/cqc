package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserDateIncome;
import com.cqc.portal.dto.resp.UserIncomeDto;
import com.cqc.portal.mapper.UserDateIncomeMapper;
import com.cqc.portal.service.UserDateIncomeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */
@Service
public class UserDateIncomeServiceImpl extends ServiceImpl<UserDateIncomeMapper, UserDateIncome> implements UserDateIncomeService {


    @Override
    public BigDecimal getIncomeByDate(String userId, String date) {
        QueryWrapper<UserDateIncome> wrapper = new QueryWrapper<UserDateIncome>()
                .eq("user_id", userId).eq("date", date);

        UserDateIncome dateIncome = baseMapper.selectOne(wrapper);
        if (dateIncome == null) {
            return BigDecimal.ZERO;
        }
        return dateIncome.getIncome() == null ? BigDecimal.ZERO : dateIncome.getIncome();
    }


    @Override
    public List<UserIncomeDto> getAgentIncome(String parentUserId, String date) {
        return baseMapper.queryAgentIncome(parentUserId, date);
    }

}
