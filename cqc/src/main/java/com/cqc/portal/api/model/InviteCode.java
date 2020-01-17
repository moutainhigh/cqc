package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@TableName("invite_code")
public class InviteCode implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 邀请码，唯一
     */
    private String inviteCode;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 过期时间
     */
    private Integer expire;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InviteCode{" +
        "id=" + id +
        ", inviteCode=" + inviteCode +
        ", userId=" + userId +
        ", expire=" + expire +
        ", createTime=" + createTime +
        "}";
    }
}
