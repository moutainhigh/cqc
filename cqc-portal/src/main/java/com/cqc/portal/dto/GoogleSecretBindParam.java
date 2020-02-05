package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GoogleSecretBindParam {

    @NotBlank(message = "secret不能为空")
    private String secret;

    @NotBlank(message = "code不能为空")
    private String code;


}
