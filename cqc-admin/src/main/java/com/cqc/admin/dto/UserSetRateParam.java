package com.cqc.admin.dto;

import com.cqc.model.UserRate;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserSetRateParam {


    @NotBlank(message = "userId不能为空")
    private String userId;

    @Valid
    private List<UserRate> rateList;



}
