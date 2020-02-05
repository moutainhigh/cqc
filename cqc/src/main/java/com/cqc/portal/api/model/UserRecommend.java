package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-02-03
 */
@TableName("user_recommend")
public class UserRecommend implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 1表示只有1级上级 2表示有2级上级，最大5  注册的时候有程序决定
     */
    private Boolean level;

    /**
     * 上级用户id
     */
    private String refUserId;

    /**
     * 上级用户账号
     */
    private String refUserAccount;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
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

    @Override
    public String toString() {
        return "UserRecommend{" +
        "userId=" + userId +
        ", account=" + account +
        ", level=" + level +
        ", refUserId=" + refUserId +
        ", refUserAccount=" + refUserAccount +
        "}";
    }
}
