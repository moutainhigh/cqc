package com.cqc.portal.api.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */
@TableName("user_date_income")
public class UserDateIncome implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id，联合主键
     */
    private String userId;

    /**
     * 日期，联合主键
     */
    private String date;

    /**
     * 推荐用户id
     */
    private String refUserId;

    /**
     * 推荐用户账号
     */
    private String refUserAccount;

    /**
     * 当日收益
     */
    private BigDecimal income;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getRefUserAccount() {
        return refUserAccount;
    }

    public void setRefUserAccount(String refUserAccount) {
        this.refUserAccount = refUserAccount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "UserDateIncome{" +
        "userId=" + userId +
        ", date=" + date +
        ", refUserId=" + refUserId +
        ", refUserAccount=" + refUserAccount +
        ", income=" + income +
        "}";
    }
}
