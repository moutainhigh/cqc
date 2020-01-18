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

    private BigDecimal cqc = BigDecimal.ZERO;


}
