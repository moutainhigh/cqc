package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.Rate;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-24
 */
public interface RateService extends IService<Rate> {

    /**
     * 获取费率
     * @param channel
     * @return
     */
    BigDecimal getRate(int channel);

}
