package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserVirtualFund;
import com.cqc.model.UserVirtualFundRecord;
import com.cqc.portal.mapper.UserVirtualFundMapper;
import com.cqc.portal.mapper.UserVirtualFundRecordMapper;
import com.cqc.portal.service.UserVirtualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户-虚拟币资产 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@Service
public class UserVirtualFundServiceImpl extends ServiceImpl<UserVirtualFundMapper, UserVirtualFund> implements UserVirtualFundService {

    @Autowired
    private UserVirtualFundMapper userVirtualFundMapper;

    @Autowired
    private UserVirtualFundRecordMapper userVirtualFundRecordMapper;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean cutFreezeBalance(String userId, int type, BigDecimal amount, String remark) {

        int i = userVirtualFundMapper.cutFreezeBalance(userId, 1, amount);
        if (i > 0) {
            // 保存记录
        }
        // 查询一遍金额
        UserVirtualFund fund = userVirtualFundMapper.selectOne(new QueryWrapper<UserVirtualFund>().eq("user_id", userId)
                .eq("type", 1));
        if (fund == null) {
            fund = new UserVirtualFund(userId, type);
        }
        UserVirtualFundRecord record = new UserVirtualFundRecord();
        record.setUserId(userId);
        record.setType(1);
        record.setAmount(amount);
        record.setBalance(fund.getAvailableBalance());
        record.setDirect(2);
        // 保存记录
        userVirtualFundRecordMapper.insert(record);
        return true;
    }
}
