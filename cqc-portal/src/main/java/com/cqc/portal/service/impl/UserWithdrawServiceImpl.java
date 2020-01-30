package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.exception.BaseException;
import com.cqc.model.UserBankCard;
import com.cqc.model.UserWithdraw;
import com.cqc.portal.mapper.UserWithdrawMapper;
import com.cqc.portal.service.UserBankCardService;
import com.cqc.portal.service.UserFundService;
import com.cqc.portal.service.UserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UserBankCardService userBankCardService;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean withDraw(String userId, String bankCardId, BigDecimal amount) {
        // 找到银行卡
        UserBankCard bankCard = userBankCardService.getById(bankCardId);
        if (bankCard == null) {
            throw new BaseException("", "银行卡错误");
        }
        // 先扣减  再保存提现申请
        boolean b = userFundService.withDraw(userId, amount);
        if (!b) {
            throw new BaseException("", "提现失败");
        }
        UserWithdraw entity = new UserWithdraw();
        entity.setUserId(userId);
        entity.setAmount(amount);
        entity.setCardNo(bankCard.getCardNo());
        entity.setBankLogo(bankCard.getBankLogo());
        entity.setBankName(bankCard.getBankName());
        entity.setRemark("用户提现");
        entity.setCost(BigDecimal.ZERO);

        super.save(entity);
        return true;
    }

}
