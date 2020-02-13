package com.cqc.portal.dto.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class UserInfo {

    private String account;

    private int real;

    private String realName;

    private boolean autoOrderStatus;

    /**
     * 可抢cqc
     */
    private BigDecimal cqc = BigDecimal.ZERO;

    /**
     * cqc总额
     */
    private BigDecimal cqcTotal = BigDecimal.ZERO;

    /**
     * 待入cqc
     */
    private BigDecimal waitPayIncome = BigDecimal.ZERO;

    /**
     * 今日收益
     */
    private BigDecimal incomeToday = BigDecimal.ZERO;


    private String province;

    private String city;

    private String region;

    private int googleBind;

    /**
     * 拼多多商家押金
     */
    private BigDecimal pddSeller = BigDecimal.ZERO;


    /**
     * 拼多多买家押金
     */
    private BigDecimal pddBuyer = BigDecimal.ZERO;


}
