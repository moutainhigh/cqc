package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglz
 */

@TableName("user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID,主键")
    private String id;

    private String refUserId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    private String payPassword;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别： 0 女 1 男")
    private Integer sex;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String imgUrl;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "生日")
    private Date birthdate;

    @ApiModelProperty(value = "状态 -1-删除 0-锁定 1-正常 2封禁")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "删除时间")
    private Date delTime;

    @ApiModelProperty(value = "封号时间")
    private Date closeTime;

    private String country;

    private String province;

    private String city;

    private String region;

    /**
     * 自动抢单状态
     */
    private Boolean autoOrderStatus;


    private String googleSecret;

    private Integer orderNotify = 0;

}