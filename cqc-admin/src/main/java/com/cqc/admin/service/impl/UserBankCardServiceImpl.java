package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.dto.UserBankCardQueryParam;
import com.cqc.admin.dto.resp.UserBankCardListDto;
import com.cqc.admin.mapper.UserBankCardMapper;
import com.cqc.admin.service.UserBankCardService;
import com.cqc.model.UserBankCard;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-银行卡 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Service
public class UserBankCardServiceImpl extends ServiceImpl<UserBankCardMapper, UserBankCard> implements UserBankCardService {

    @Override
    public Page<UserBankCardListDto> pageList(UserBankCardQueryParam param) {
        Page<UserBankCardListDto> page = new Page<>(param.getPageNum(), param.getPageSize());
        baseMapper.pageList(page, param);
        return page;
    }
}
