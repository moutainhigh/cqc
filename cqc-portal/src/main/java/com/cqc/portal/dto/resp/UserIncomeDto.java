package com.cqc.portal.dto.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
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

    public UserIncomeDto(String userId, String account, String date) {
        this.userId = userId;
        this.account = account;
        this.date = date;
    }
}
