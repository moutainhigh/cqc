package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
@TableName("receive_code")
public class ReceiveCode implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 收款码
     */
    private String code;

    /**
     * 1 支付宝 2微信
     */
    private Boolean type;

    /**
     * 状态 1-启动 0关闭
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReceiveCode{" +
        "id=" + id +
        ", userId=" + userId +
        ", name=" + name +
        ", code=" + code +
        ", type=" + type +
        ", status=" + status +
        "}";
    }
}
