package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class RegisterParam {

    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @NotBlank(message = "用户名不能为空")
    private String account;

    @NotBlank(message = "邀请码不能为空")
    private String inviteCode;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;


}
