package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID,主键
     */
    private String id;

    /**
     * 邀请人id
     */
    private String refUserId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别： 0 女 1 男
     */
    private Boolean sex;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String imgUrl;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 生日
     */
    private LocalDateTime birthdate;

    /**
     * 国家id
     */
    private String countryId;

    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 状态 -1-删除 0-锁定 1-正常 2封禁
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 删除时间
     */
    private LocalDateTime delTime;

    /**
     * 封号时间
     */
    private LocalDateTime closeTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDelTime() {
        return delTime;
    }

    public void setDelTime(LocalDateTime delTime) {
        this.delTime = delTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", refUserId=" + refUserId +
        ", account=" + account +
        ", password=" + password +
        ", payPassword=" + payPassword +
        ", nickName=" + nickName +
        ", realName=" + realName +
        ", sex=" + sex +
        ", mobile=" + mobile +
        ", imgUrl=" + imgUrl +
        ", email=" + email +
        ", birthdate=" + birthdate +
        ", countryId=" + countryId +
        ", countryName=" + countryName +
        ", status=" + status +
        ", createTime=" + createTime +
        ", delTime=" + delTime +
        ", closeTime=" + closeTime +
        "}";
    }
}
