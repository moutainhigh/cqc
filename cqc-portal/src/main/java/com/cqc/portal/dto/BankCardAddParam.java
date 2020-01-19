package com.cqc.portal.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BankCardAddParam {

    private String bankId;

    @NotBlank(message = "银行卡号不能为空")
    private String cardNo;


}
