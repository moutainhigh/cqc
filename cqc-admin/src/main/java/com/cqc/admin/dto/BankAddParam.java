package com.cqc.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BankAddParam {

    @NotBlank(message = "银行名称不等于空")
    private String name;

    @NotBlank(message = "银行logo不等于空")
    private String logo;
}
