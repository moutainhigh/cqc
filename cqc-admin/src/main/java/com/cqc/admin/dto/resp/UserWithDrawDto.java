package com.cqc.admin.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserWithDrawDto {

    private String id;

    private String userId;

    private String account;

    private BigDecimal amount;

    private String bankName;

    private String cardNo;

    private String bankLogo;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}
