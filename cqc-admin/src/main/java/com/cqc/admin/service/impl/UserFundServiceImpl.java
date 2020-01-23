package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.mapper.UserFundMapper;
import com.cqc.admin.mapper.UserFundRecordMapper;
import com.cqc.admin.service.UserFundService;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserFund;
import com.cqc.model.UserFundRecord;
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
    public boolean recharge(String userId, BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            return false;
        }
        int i = userFundMapper.addBalance(userId, amount);
        if (i != 1) {
            return false;
        }
        UserFund userFund = this.getFund(userId);
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(1);
        record.setAmount(amount);
        record.setDirect(1);
        record.setBalance(userFund.getBalance());
        record.setRemark("后台管理员手工充值");

        int j = userFundRecordMapper.insert(record);
        if (j != 1) {
            // 保存失败 回滚数据
            throw new BaseException("", "保存失败");
        }
        return true;
    }
}
