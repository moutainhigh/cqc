package com.cqc.portal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.model.UserDateIncome;
import com.cqc.model.UserFund;
import com.cqc.model.UserFundRecord;
import com.cqc.portal.mapper.UserFundMapper;
import com.cqc.portal.mapper.UserFundRecordMapper;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@Slf4j
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
            dateIncome.setUserAccount(refUserAccount);
            dateIncome.setDate(DateUtil.format(new Date(), "yyyyMMdd"));
            dateIncome.setIncome(income);
            dateIncome.setTeamIncome(income);
            dateIncome.setRefUserId(refUserId);
            boolean b = userDateIncomeService.updateUserIncome(dateIncome);
            if (!b) {
                // 手动回滚
                throw new BaseException("", "保存收益数据有误，回滚数据");
            }
        }
        return true;
    }

    @Override
    public boolean addRefIncome(String userId, BigDecimal income) {
        boolean rs = addBalance(userId, income, 8, "推广佣金");
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
            dateIncome.setUserAccount(refUserAccount);
            dateIncome.setDate(DateUtil.format(new Date(), "yyyyMMdd"));
            dateIncome.setIncome(income);
            dateIncome.setTeamIncome(income);
            dateIncome.setRefUserId(refUserId);
            boolean b = userDateIncomeService.updateUserIncome(dateIncome);
            if (!b) {
                // 手动回滚
                throw new BaseException("", "保存收益数据有误，回滚数据");
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addBalance(String userId, BigDecimal amount, int type, String remark) {
        UserFund userFund = this.getFund(userId);

        userFundMapper.addCommission(userId, amount);
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(type);
        record.setAmount(amount);
        record.setDirect(1);
        record.setPreBalance(userFund.getAvailableBalance());
        record.setBalance(userFund.getAvailableBalance().add(amount));
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
        record.setBalance(userFund.getAvailableBalance());
        record.setRemark(remark);
        userFundRecordMapper.insert(record);
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean freezeBalance(String userId, BigDecimal amount) {
        UserFund userFund = this.getFund(userId);
        int i = userFundMapper.cutBalance(userId, amount);
        if (i <= 0) {
            return false;
        }
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(6);
        record.setAmount(amount);
        record.setDirect(2);
        record.setPreBalance(userFund.getAvailableBalance());
        record.setBalance(userFund.getAvailableBalance().subtract(amount));
        record.setRemark("抢单付款");
        userFundRecordMapper.insert(record);
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean unFreezeBalance(String userId, BigDecimal amount) {
        UserFund userFund = this.getFund(userId);
        int i = userFundMapper.addBalance(userId, amount);
        if (i <= 0) {
            return false;
        }
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(17);
        record.setAmount(amount);
        record.setDirect(1);
        record.setPreBalance(userFund.getAvailableBalance());
        record.setBalance(userFund.getAvailableBalance().add(amount));
        record.setRemark("取消订单退回");
        userFundRecordMapper.insert(record);
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean withDraw(String userId, BigDecimal amount) {
        UserFund userFund = this.getFund(userId);
        if (userFund.getAvailableBalance().compareTo(amount) < 0) {
            // 报错
            throw new BaseException("", "可用余额不足");
        }
        int i = userFundMapper.cutBalance(userId, amount);
        if (i != 1) {
            return false;
        }
        // 保存记录
        // 保存记录
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(4);
        record.setAmount(amount);
        record.setDirect(2);
        record.setPreBalance(userFund.getAvailableBalance());
        record.setBalance(userFund.getAvailableBalance().subtract(amount));
        record.setRemark("提现");
        userFundRecordMapper.insert(record);
        return true;
    }

    @Override
    public boolean deposit(String userId, int type, BigDecimal amount) {
        UserFund userFund = this.getFund(userId);
        if (userFund.getAvailableBalance().compareTo(amount) < 0) {
            // 报错
            throw new BaseException("", "可用余额不足");
        }
        int i = 0;
        if (type == 1) {
            if (userFund.getPddSeller().compareTo(BigDecimal.ZERO) > 0) {
                throw new BaseException("", "已经缴纳了拼多多商家押金");
            }
            i = userFundMapper.cutBalance4PddSeller(userId, amount);
        } else if (type == 2) {
            if (userFund.getPddBuyer().compareTo(BigDecimal.ZERO) > 0) {
                throw new BaseException("", "已经缴纳了拼多多买家押金");
            }
            i = userFundMapper.cutBalance4PddBuyer(userId, amount);
        }
        if (i != 1) {
            return false;
        }
        UserFundRecord record = new UserFundRecord();
        record.setUserId(userId);
        record.setType(16);
        record.setAmount(amount);
        record.setDirect(2);
        record.setPreBalance(userFund.getAvailableBalance());
        record.setBalance(userFund.getAvailableBalance().subtract(amount));
        record.setRemark("提现");
        userFundRecordMapper.insert(record);
        return true;
    }
}
