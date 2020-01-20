package com.cqc.portal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.User;
import com.cqc.model.UserDateIncome;
import com.cqc.model.UserFund;
import com.cqc.model.UserFundRecord;
import com.cqc.portal.mapper.UserFundMapper;
import com.cqc.portal.mapper.UserFundRecordMapper;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

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

    @Autowired
    private UserDateIncomeService userDateIncomeService;

    @Autowired
    private UserService userService;

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
    public boolean addIncome(String userId, BigDecimal income) {
        boolean rs = addBalance(userId, income, 7, "抢单佣金");
        if (rs) {
            // 查用户
            String refUserId = "";
            String refUserAccount = "";
            User user = userService.getUser(userId);
            if (user != null) {
                refUserId = user.getRefUserId();
                refUserAccount = user.getAccount();
            }
            // 保存 user_date_income表数据
            UserDateIncome dateIncome = new UserDateIncome();
            dateIncome.setUserId(userId);
            dateIncome.setDate(DateUtil.format(new Date(), "yyyyMMdd"));
            dateIncome.setIncome(income);
            dateIncome.setRefUserId(refUserId);
            dateIncome.setRefUserAccount(refUserAccount);
            userDateIncomeService.saveOrUpdate(dateIncome);
        }
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addBalance(String userId, BigDecimal amount, int type, String remark) {
        userFundMapper.addCommission(userId, amount);

        UserFund userFund = this.getFund(userId);
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

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean cutFreezeBalance(String userId, BigDecimal amount, int type, String remark) {
        int i = userFundMapper.cutFreezeBalance(userId, amount);
        if (i <= 0) {
            return false;
        }
        UserFund userFund = this.getFund(userId);
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(type);
        record.setAmount(amount);
        record.setDirect(2);
        record.setBalance(userFund.getBalance());
        record.setRemark(remark);
        userFundRecordMapper.insert(record);
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean freezeBalance(String userId, BigDecimal amount) {
        int i = userFundMapper.freezeBalance(userId, amount);
        return i == 1;
    }
}
