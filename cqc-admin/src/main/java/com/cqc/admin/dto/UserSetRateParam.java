package com.cqc.admin.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserSetRateParam {


    @NotBlank(message = "userId不能为空")
    private String userId;

    private int channel;

    @NotNull(message = "rate不能为空")
    @Min(value = 0)
    private BigDecimal rate;



}
