package com.cqc.admin.dto.resp;

import lombok.Data;

import java.util.Date;

@Data
public class UserBankCardListDto {

    private String id;

    private String userId;

    private String account;

    private String bankName;

    private String cardNo;

    private String bankLogo;

    private Integer status;

    private Date createTime;

}
