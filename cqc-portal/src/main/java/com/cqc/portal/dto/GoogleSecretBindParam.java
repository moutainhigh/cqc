package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GoogleSecretBindParam {

    @NotBlank(message = "不能为空")
    private String secret;

    @NotNull(message = "code不能为空")
    private Integer code;


}
