package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-02-15
 */
@Data
@TableName("order_publish")
public class OrderPublish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 发布者昵称
     */
    private String publisher;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 渠道 1 支付宝 2微信
     */
    private Integer channel;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * -1放单中 0 代付款 1已付款 2已入账 3订单取消 4异常 5已过期 6金额不符 7付款失败
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 付款时间
     */
    private Date payTime;

}
