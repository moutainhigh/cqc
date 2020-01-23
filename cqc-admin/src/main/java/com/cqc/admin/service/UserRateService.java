package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserRate;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户费率 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserRateService extends IService<UserRate> {

    /**
     * 设置费率
     * @param userId
     * @param list
     * @return
     */
    boolean setUserRate(String userId, List<UserRate> list);

    /**
     * 初始化费率
     * @param userId
     * @return
     */
    boolean initUserRate(String userId);

}
