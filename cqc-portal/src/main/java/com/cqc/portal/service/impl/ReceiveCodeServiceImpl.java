package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.ReceiveCode;
import com.cqc.portal.mapper.ReceiveCodeMapper;
import com.cqc.portal.service.ReceiveCodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
@Service
public class ReceiveCodeServiceImpl extends ServiceImpl<ReceiveCodeMapper, ReceiveCode> implements ReceiveCodeService {


    @Override
    public ReceiveCode getCode(String userId, int channel) {
        List<ReceiveCode> list = baseMapper.selectList(new QueryWrapper<ReceiveCode>().eq("user_id", userId)
                .eq("channel", channel).eq("status", 1));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public int getCodeNumber(String userId) {
        return baseMapper.selectCount(new QueryWrapper<ReceiveCode>().eq("user_id", userId)
        .eq("status", 1));
    }
}
