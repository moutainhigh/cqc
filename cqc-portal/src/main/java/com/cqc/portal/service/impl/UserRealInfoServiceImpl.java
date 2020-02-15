package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserRealInfo;
import com.cqc.portal.dto.UserRealInfoAddParam;
import com.cqc.portal.mapper.UserRealInfoMapper;
import com.cqc.portal.service.UserRealInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户实名信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@Service
public class UserRealInfoServiceImpl extends ServiceImpl<UserRealInfoMapper, UserRealInfo> implements UserRealInfoService {



    @Override
    public UserRealInfo getRealInfo(String userId) {
        UserRealInfo realInfo = baseMapper.selectOne(new QueryWrapper<UserRealInfo>().eq("user_id", userId)
                .eq("type", 1).eq("status", 1));
        return realInfo;
    }


    @Override
    public boolean apply(UserRealInfoAddParam param) {
        UserRealInfo realInfo = getRealInfo(param.getUserId());
        if (realInfo != null) {
            throw new BaseException("", "已经提交实名信息");
        }
        // 身份证号不能重复
        UserRealInfo entity = baseMapper.selectOne(new QueryWrapper<UserRealInfo>().eq("id_number", param.getIdNumber())
                .eq("type", 1));
        if (entity != null) {
            throw new BaseException("", "该身份证号已经提交实名");
        }

        realInfo = new UserRealInfo();
        BeanUtils.copyProperties(param, realInfo);



        return super.save(realInfo);
    }
}
