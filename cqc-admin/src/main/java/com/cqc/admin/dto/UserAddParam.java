package com.cqc.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserAddParam {

    @NotBlank(message = "account不能为空")
    private String account;

    @NotBlank(message = "password不能为空")
    private String password;

    @NotBlank(message = "confirmPassword不能为空")
    private String confirmPassword;

}
