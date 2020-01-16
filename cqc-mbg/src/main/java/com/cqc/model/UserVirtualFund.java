package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户-虚拟币资产
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@TableName("user_virtual_fund")
public class UserVirtualFund implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID， 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 资产类型 1-cqc
     */
    private Boolean type;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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
        return "UserVirtualFund{" +
        "id=" + id +
        ", userId=" + userId +
        ", type=" + type +
        ", balance=" + balance +
        ", freezeBalance=" + freezeBalance +
        ", availableBalance=" + availableBalance +
        "}";
    }
}
