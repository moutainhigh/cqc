package com.cqc.portal.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-18
 **/
@Data
public class ReceiveCodeAddParam {


    private String receiveName;

    private String code;

    private Integer type;

    private String codeImg;

    @NotBlank(message = "谷歌验证码不能为空")
    @Length(max = 6, message = "最大长度6")
    private String googleCode;

}
