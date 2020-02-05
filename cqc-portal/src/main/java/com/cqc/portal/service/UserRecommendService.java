package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserRecommend;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-03
 */
public interface UserRecommendService extends IService<UserRecommend> {

    /**
     * 查询最高
     * @param userId
     * @return
     */
    Integer getMaxParentLevel(String userId);

}
