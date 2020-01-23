package com.cqc.admin.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wanglz
 * @date 2020-01-08
 **/
@Data
public class UserListDto {

    @ApiModelProperty(value = "ID,主键")
    private String id;

    @ApiModelProperty(value = "账号")
    private String account;

    private String nickName;

    private String realName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "cqc可用余额")
    private BigDecimal cqcBalance;

    private Integer status;

}
