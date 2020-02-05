package com.cqc.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class LoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Length(max = 6, message = "最大长度6")
    private String googleCode;

}
