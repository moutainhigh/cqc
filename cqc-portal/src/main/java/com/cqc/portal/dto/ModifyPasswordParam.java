package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "code不能为空")
    private String code;

}
