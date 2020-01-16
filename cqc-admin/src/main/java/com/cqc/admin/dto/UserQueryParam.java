package com.cqc.admin.dto;

import com.cqc.common.api.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wanglz
 * @date 2020-01-08
 **/

@Data
public class UserQueryParam extends PageQuery {

    @ApiModelProperty(value = "用户名")
    private String account;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

}
