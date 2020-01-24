package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.Rate;
import com.cqc.portal.mapper.RateMapper;
import com.cqc.portal.service.RateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-24
 */
@Service
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements RateService {

    @Override
    public BigDecimal getRate(int channel) {
        Rate rate = baseMapper.selectById(String.valueOf(channel));
        if (rate == null) {
            return BigDecimal.ZERO;
        }
        return rate.getRate();
    }

}
