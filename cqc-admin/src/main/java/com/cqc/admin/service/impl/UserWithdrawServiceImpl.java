package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.dto.WithDrawQueryParam;
import com.cqc.admin.dto.resp.UserWithDrawDto;
import com.cqc.admin.mapper.UserFundMapper;
import com.cqc.admin.mapper.UserWithdrawMapper;
import com.cqc.admin.service.UserFundService;
import com.cqc.admin.service.UserWithdrawService;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserWithdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 提现记录 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
@Service
public class UserWithdrawServiceImpl extends ServiceImpl<UserWithdrawMapper, UserWithdraw> implements UserWithdrawService {

    @Autowired
    private UserFundService userFundService;

    @Override
    public Page<UserWithDrawDto> listPage(WithDrawQueryParam param) {
        Page<UserWithDrawDto> page = new Page<>(param.getPageNum(), param.getPageSize());
        baseMapper.pageList(page, param);
        return page;
    }

    @Override
    public boolean back(String id) {
        UserWithdraw withdraw = super.getById(id);
        if (withdraw == null) {
            return false;
        }
        // 先扣减  再保存提现申请
        boolean b = userFundService.backWithDraw(withdraw.getUserId(), withdraw.getAmount());
        if (!b) {
            throw new BaseException("", "操作失败");
        }
        // 更改提现状态
        UserWithdraw entity = new UserWithdraw();
        entity.setId(id);
        entity.setStatus(2);
        return super.updateById(entity);
    }
}
