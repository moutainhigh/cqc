package com.cqc.admin.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserRealInfoDto {

    private String userId;

    private String account;

    private String mobile;

    private String realName;

    private String idNumber;

    /**
     * 证件照1
     */
    private String img1;

    /**
     * 证件照2
     */
    private String img2;

    /**
     * 状态 -1删除 0 禁用 1 正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;


}
