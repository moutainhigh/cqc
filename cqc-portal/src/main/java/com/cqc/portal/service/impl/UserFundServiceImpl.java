package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.UserFund;
import com.cqc.model.UserFundRecord;
import com.cqc.portal.mapper.UserFundMapper;
import com.cqc.portal.mapper.UserFundRecordMapper;
import com.cqc.portal.service.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户金额 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */
@Service
public class UserFundServiceImpl extends ServiceImpl<UserFundMapper, UserFund> implements UserFundService {

    @Autowired
    private UserFundMapper userFundMapper;

    @Autowired
    private UserFundRecordMapper userFundRecordMapper;


    @Override
    public UserFund getFund(String userId) {
        UserFund userFund = userFundMapper.selectOne(new QueryWrapper<UserFund>().eq("user_id", userId));
        if (userFund == null) {
            userFund = new UserFund(userId);
        }
        return userFund;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addBalance(String userId, BigDecimal amount, int type, String remark) {
        userFundMapper.addAmount(userId, amount);

        UserFund userFund = userFundMapper.selectOne(new QueryWrapper<UserFund>().eq("user_id", userId));
        if (userFund == null) {
            userFund = new UserFund(userId);
        }
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(type);
        record.setAmount(amount);
        record.setDirect(1);
        record.setBalance(userFund.getBalance());
        record.setRemark(remark);
        userFundRecordMapper.insert(record);

        return true;
    }


}
