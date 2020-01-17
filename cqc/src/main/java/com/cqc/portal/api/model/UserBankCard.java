package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户-银行卡
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@TableName("user_bank_card")
public class UserBankCard implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行简称
     */
    private String bankCode;

    /**
     * 银行logo
     */
    private String bankLogo;

    /**
     * 银行卡类型：1-借记卡 2-贷记合一 3-贷记卡
     */
    private Boolean cardType;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 账户类型 1-个人账户 2企业账户
     */
    private Boolean accountType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态 0认证中 1认证通过 2 不通过
     */
    private Boolean status;


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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public Boolean getCardType() {
        return cardType;
    }

    public void setCardType(Boolean cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getAccountType() {
        return accountType;
    }

    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserBankCard{" +
        "id=" + id +
        ", userId=" + userId +
        ", bankName=" + bankName +
        ", bankCode=" + bankCode +
        ", bankLogo=" + bankLogo +
        ", cardType=" + cardType +
        ", cardNo=" + cardNo +
        ", realName=" + realName +
        ", accountType=" + accountType +
        ", mobile=" + mobile +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
