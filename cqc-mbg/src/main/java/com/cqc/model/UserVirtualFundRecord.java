package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户-虚拟币记录
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@TableName("user_virtual_fund_record")
public class UserVirtualFundRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资产类型 1 cqc
     */
    private Boolean type;

    /**
     * 方向 1 增加 2减少
     */
    private Integer direct;

    /**
     * 当前余额
     */
    private BigDecimal balance;


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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserVirtualFundRecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", amount=" + amount +
        ", type=" + type +
        ", direct=" + direct +
        ", balance=" + balance +
        "}";
    }
}
