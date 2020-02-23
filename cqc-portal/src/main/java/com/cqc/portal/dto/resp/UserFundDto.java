package com.cqc.portal.dto.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-18
 **/

@Data
public class UserFundDto {


    private Boolean autoOrderStatus;

    private BigDecimal cqc = BigDecimal.ZERO;


    /**
     * 拼多多商家押金
     */
    private BigDecimal pddSeller = BigDecimal.ZERO;


    /**
     * 拼多多买家押金
     */
    private BigDecimal pddBuyer = BigDecimal.ZERO;

    private Boolean newOrderStatus = false;

    private Integer orderNotify = 0;
}
