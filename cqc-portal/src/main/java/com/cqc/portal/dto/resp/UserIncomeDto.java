package com.cqc.portal.dto.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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

    private Date registerTime;

    public UserIncomeDto(String userId, String account, String date) {
        this.userId = userId;
        this.account = account;
        this.date = date;
    }

    public UserIncomeDto(String userId, String account, Date registerTime) {
        this.userId = userId;
        this.account = account;
        this.registerTime = registerTime;
    }
}
