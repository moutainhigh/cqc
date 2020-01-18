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


}
