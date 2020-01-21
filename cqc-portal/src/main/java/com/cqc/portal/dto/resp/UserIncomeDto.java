package com.cqc.portal.dto.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserIncomeDto {

    private String userId;

    private String id;

    private String account;

    private String date;

    private String refUserId;

    private String refUserAccount;

    private BigDecimal income = BigDecimal.ZERO;

    private BigDecimal teamIncome = BigDecimal.ZERO;



}
