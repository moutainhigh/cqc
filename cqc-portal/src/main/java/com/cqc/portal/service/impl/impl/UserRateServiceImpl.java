package com.cqc.portal.service.impl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.dao.UserRateMapper;
import com.cqc.admin.service.UserRateService;
import com.cqc.model.UserRate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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





    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean setUserRate(String userId, int channel, BigDecimal rate) {

        UserRate userRate = new UserRate();
        userRate.setUserId(userId);
        userRate.setChannel(channel);
        userRate.setRate(rate);

        return saveOrUpdate(userRate);
    }

}
