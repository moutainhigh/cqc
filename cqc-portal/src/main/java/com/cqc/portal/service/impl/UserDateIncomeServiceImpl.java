package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.constant.Constants;
import com.cqc.model.UserDateIncome;
import com.cqc.model.UserRecommend;
import com.cqc.portal.dto.resp.UserIncomeDto;
import com.cqc.portal.mapper.UserDateIncomeMapper;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserRecommendService;
import com.cqc.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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

    @Autowired
    private UserRecommendService userRecommendService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDateIncomeService userDateIncomeService;

    @Autowired
    private UserFundService userFundService;

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
    public List<UserDateIncome> getAgentIncome(List<String> userIds, String date) {
        return baseMapper.queryAgentIncome(userIds, date);
    }

    @Override
    public boolean updateUserIncome(UserDateIncome entity) {
        return baseMapper.updateIncome(entity) > 0;
    }

    @Override
    public boolean saveUserIncome(String userId, BigDecimal amount, String date) {
        List<UserRecommend> recommendList = userService.queryParents(userId);
        if (CollectionUtils.isEmpty(recommendList)) {
            return false;
        }
        recommendList.sort((a, b) -> a.getLevel().compareTo(b.getLevel()));

        List<UserDateIncome> list = new ArrayList<>(recommendList.size());
        for (UserRecommend userRecommend : recommendList) {
            UserDateIncome userDateIncome = new UserDateIncome();
            userDateIncome.setDate(date);
            userDateIncome.setUserId(userRecommend.getRefUserId());
            userDateIncome.setUserAccount(userRecommend.getRefUserAccount());
            userDateIncome.setIncome(amount.multiply(Constants.RATE_CHA));
            userDateIncome.setTeamIncome(amount.multiply(Constants.RATE_CHA.multiply(new BigDecimal(userRecommend.getLevel()))));
            list.add(userDateIncome);

            // 保存推广佣金
            userFundService.addRefIncome(userDateIncome.getUserId(), userDateIncome.getIncome());
        }

        return false;
    }
}
