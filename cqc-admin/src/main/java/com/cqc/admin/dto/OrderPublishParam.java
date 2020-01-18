package com.cqc.admin.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-18
 **/

@Data
public class OrderPublishParam {

    private String publisher;

    /**
     * 渠道 1 支付宝 2微信
     */
    private Integer channel;

    /**
     * 金额
     */
    private BigDecimal amount;



}
