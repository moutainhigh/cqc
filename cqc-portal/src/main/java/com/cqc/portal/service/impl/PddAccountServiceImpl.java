package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.PddAccount;
import com.cqc.portal.mapper.PddAccountMapper;
import com.cqc.portal.service.PddAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-10
 */
@Service
public class PddAccountServiceImpl extends ServiceImpl<PddAccountMapper, PddAccount> implements PddAccountService {

    @Override
    public List<PddAccount> getByUser(String userId) {
        QueryWrapper<PddAccount> wrapper = new QueryWrapper<PddAccount>()
                .eq("user_id", userId);
        return baseMapper.selectList(wrapper);
    }
}
