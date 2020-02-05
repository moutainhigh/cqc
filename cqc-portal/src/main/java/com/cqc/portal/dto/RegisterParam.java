package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class RegisterParam {

    @NotEmpty(message = "手机号码不能为空")
    private String mobile;

    @NotEmpty(message = "用户名不能为空")
    private String account;

    private String inviteCode;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "确认密码不能为空")
    private String confirmPassword;

    @NotEmpty(message = "短信验证码不能为空")
    private String smsCode;


}
