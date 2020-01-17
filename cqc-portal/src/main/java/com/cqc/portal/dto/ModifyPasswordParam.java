package com.cqc.portal.dto;

import lombok.Data;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class ModifyPasswordParam {

    /**
     * 旧密码
     */
    private String password;

    private String newPassword;

    private String confirmPassword;

    private String code;



}
