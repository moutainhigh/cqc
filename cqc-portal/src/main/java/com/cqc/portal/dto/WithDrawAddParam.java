package com.cqc.portal.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithDrawAddParam {

    private String userId;

    private String bankCardId;

    private BigDecimal amount;
}
