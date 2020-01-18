package com.cqc.portal.api.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
@TableName("order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 发布者昵称
     */
    private String publisher;

    /**
     * 渠道 1 支付宝 2微信
     */
    private Integer channel;

    /**
     * 抢单用户id
     */
    private String userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 0 代付款 1已支付 2已入账 3订单已过期
     */
    private Boolean status;

    /**
     * 抢单时间
     */
    private LocalDateTime createTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
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

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", publisher=" + publisher +
        ", channel=" + channel +
        ", userId=" + userId +
        ", amount=" + amount +
        ", status=" + status +
        ", createTime=" + createTime +
        ", expireTime=" + expireTime +
        ", payTime=" + payTime +
        "}";
    }
}
