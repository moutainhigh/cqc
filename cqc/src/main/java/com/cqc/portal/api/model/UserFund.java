package com.cqc.portal.api.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户金额
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */
@TableName("user_fund")
public class UserFund implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 用户id
     */
    private String usreId;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    private BigDecimal freezeBalance;

    /**
     * 可用金额
     */
    private BigDecimal availableBalance;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsreId() {
        return usreId;
    }

    public void setUsreId(String usreId) {
        this.usreId = usreId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "UserFund{" +
        "id=" + id +
        ", usreId=" + usreId +
        ", balance=" + balance +
        ", freezeBalance=" + freezeBalance +
        ", availableBalance=" + availableBalance +
        "}";
    }
}
