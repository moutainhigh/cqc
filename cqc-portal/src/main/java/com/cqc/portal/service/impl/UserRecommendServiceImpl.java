package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserRecommend;
import com.cqc.portal.mapper.UserRecommendMapper;
import com.cqc.portal.service.UserRecommendService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-03
 */
@Service
public class UserRecommendServiceImpl extends ServiceImpl<UserRecommendMapper, UserRecommend> implements UserRecommendService {

    @Override
    public Integer getMaxParentLevel(String userId) {
        return null;
    }
}
