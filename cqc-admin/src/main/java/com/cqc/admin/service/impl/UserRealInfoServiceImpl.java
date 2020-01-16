package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.dto.UserRealInfoQueryParam;
import com.cqc.admin.dto.resp.UserRealInfoDto;
import com.cqc.admin.mapper.UserRealInfoMapper;
import com.cqc.admin.service.UserRealInfoService;
import com.cqc.model.UserRealInfo;
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
    public Page<UserRealInfoDto> listPage(UserRealInfoQueryParam param) {
        Page<UserRealInfoDto> page = new Page<>(param.getPageNum(), param.getPageSize());

        return baseMapper.listPage(page, param.getType(), param.getStatus());
    }

}
