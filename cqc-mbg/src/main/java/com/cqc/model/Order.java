package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */

@Data
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    private String orderSn;

    /**
     * 放单号
     */
    private String publishOrderSn;


    /**
     * 放单id
     */
    private String publishOrderId;


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
     * 抢单用户账号
     */
    private String account;
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 0 代付款 1已支付 2已入账 3订单已过期
     */
    private Integer status;

    /**
     * 发布时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 过期时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
     * 付款时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 抢单时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyTime;

    /**
     * 取消时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /**
     * 佣金
     */
    private BigDecimal income;

    private BigDecimal rate;

    /**
     * 收款码图片
     */
    private String receiveCodeImg;

    /**
     * 收款码id
     */
    private String receiveCodeId;

    /**
     * 收款码姓名
     */
    private String receiveCodeName;

    private String country;

    private String province;

    private String city;

    private String region;


    private String pddAccountId;

    private String pddAccount;

}
