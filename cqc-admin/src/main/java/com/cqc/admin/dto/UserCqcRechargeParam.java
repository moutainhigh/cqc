package com.cqc.admin.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserCqcRechargeParam {

    @NotBlank(message = "userId不能为空")
    private String userId;

    @Min(value = 1)
    private BigDecimal cgc = BigDecimal.ZERO;




}
