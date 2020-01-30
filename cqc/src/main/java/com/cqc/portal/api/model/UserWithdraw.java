package com.cqc.portal.api.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 提现记录
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
@TableName("user_withdraw")
public class UserWithdraw implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 银行logo
     */
    private String bankLogo;

    /**
     * 方式：1-人工转账 2-系统转账
     */
    private Boolean type;

    /**
     * 提现费用
     */
    private BigDecimal cost;

    /**
     * 状态：0-转账中 1-成功 2-失败
     */
    private Boolean status;

    /**
     * 第三方转账单号
     */
    private String thirdTradeNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 到账时间
     */
    private LocalDateTime successTime;


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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(LocalDateTime successTime) {
        this.successTime = successTime;
    }

    @Override
    public String toString() {
        return "UserWithdraw{" +
        "id=" + id +
        ", userId=" + userId +
        ", amount=" + amount +
        ", bankName=" + bankName +
        ", cardNo=" + cardNo +
        ", bankLogo=" + bankLogo +
        ", type=" + type +
        ", cost=" + cost +
        ", status=" + status +
        ", thirdTradeNo=" + thirdTradeNo +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", successTime=" + successTime +
        "}";
    }
}
