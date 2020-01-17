package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserRealInfo;
import com.cqc.portal.dto.UserRealInfoAddParam;

/**
 * <p>
 * 用户实名信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserRealInfoService extends IService<UserRealInfo> {

    /**
     * 获取用户实名信息
     * @param userId
     * @return
     */
    UserRealInfo getRealInfo(String userId);

    /**
     * 提交实名信息
     * @param param
     * @return
     */
    boolean apply(UserRealInfoAddParam param);


}
