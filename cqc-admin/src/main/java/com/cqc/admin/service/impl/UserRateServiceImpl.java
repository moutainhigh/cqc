package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.admin.dao.UserRateMapper;
import com.cqc.admin.service.UserRateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserRate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
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





    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean setUserRate(String userId,  List<UserRate> list) {

        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (UserRate userRate : list) {
            userRate.setUserId(userId);
        }
        // 保存数据
        // 先删除
        baseMapper.delete(new QueryWrapper<UserRate>().eq("user_id", userId));
        // 再保存
        boolean b = super.saveBatch(list);
        return b;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean initUserRate(String userId) {
        // 初始化费率
        List<UserRate> list = new ArrayList<>();
        list.add(new UserRate(userId, 1, new BigDecimal("0.00001")));
        list.add(new UserRate(userId, 2, new BigDecimal("0.00001")));
        return super.saveBatch(list);
    }
}
