package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.UserRate;

import java.math.BigDecimal;

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
     * @param channel
     * @param rate
     * @return
     */
    boolean setUserRate(String userId, int channel, BigDecimal rate);


}
