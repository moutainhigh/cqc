package com.cqc.portal.dto;

import lombok.Data;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class UserRealInfoAddParam {

    private String userId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 证件号码
     */
    private String idNumber;

    private String qq;

    private String email;

    private String telephone;



}
