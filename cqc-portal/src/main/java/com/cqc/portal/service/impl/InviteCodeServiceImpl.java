package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.InviteCode;
import com.cqc.portal.mapper.InviteCodeMapper;
import com.cqc.portal.service.InviteCodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Service
public class InviteCodeServiceImpl extends ServiceImpl<InviteCodeMapper, InviteCode> implements InviteCodeService {
    @Override
    public String getUserId(String code) {

        InviteCode inviteCode = baseMapper.selectOne(new QueryWrapper<InviteCode>().eq("invite_code", code));
        if (inviteCode == null) {
            return null;
        }
        return inviteCode.getUserId();
    }
}
