package com.cqc.portal.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PddAccountAddParam {


    @NotBlank(message = "账号不能为空")
    private String pddAccount;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "type不能为空")
    private Integer type;

    @Length(max = 6, message = "最大长度6")
    private String googleCode;

}
