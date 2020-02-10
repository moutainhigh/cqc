package com.cqc.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-02-10
 **/

@Data
public class GoogleResetParam {

    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;

}
