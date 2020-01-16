package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 实名认证申请
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@TableName("user_real_apply")
public class UserRealApply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键，ID
     */
    private String id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 认证类型 1-身份证 2-护照
     */
    private Boolean type;

    /**
     * 身份证正面照
     */
    private String cardImgFront;

    /**
     * 身份证反面照
     */
    private String cardImgReverse;

    /**
     * 审核状态 0-待审核  1-审核通过 2-审核不通过
     */
    private Boolean status;

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

    public String getCardImgFront() {
        return cardImgFront;
    }

    public void setCardImgFront(String cardImgFront) {
        this.cardImgFront = cardImgFront;
    }

    public String getCardImgReverse() {
        return cardImgReverse;
    }

    public void setCardImgReverse(String cardImgReverse) {
        this.cardImgReverse = cardImgReverse;
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

    @Override
    public String toString() {
        return "UserRealApply{" +
        "id=" + id +
        ", userId=" + userId +
        ", type=" + type +
        ", cardImgFront=" + cardImgFront +
        ", cardImgReverse=" + cardImgReverse +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
