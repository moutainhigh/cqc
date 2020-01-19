package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserRate;
import com.cqc.portal.mapper.UserRateMapper;
import com.cqc.portal.service.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户费率 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@Service
public class UserRateServiceImpl extends ServiceImpl<UserRateMapper, UserRate> implements UserRateService {

    @Autowired
    private UserRateMapper userRateMapper;

    @Override
    public BigDecimal getRate(String userId, int channel) {
        QueryWrapper<UserRate> wrapper = new QueryWrapper<UserRate>().eq("user_id", userId).eq("channel", channel);
        List<UserRate> list = userRateMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }
        return list.get(0).getRate();
    }
}
