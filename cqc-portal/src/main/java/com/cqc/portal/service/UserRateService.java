package com.cqc.portal.service;

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
     * 获取费率
     * @param userId
     * @param channel
     * @return
     */
    BigDecimal getRate(String userId, int channel);

}
