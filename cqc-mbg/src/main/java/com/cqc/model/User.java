package com.cqc.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglz
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID,主键")
    private String id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

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





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", realName=").append(realName);
        sb.append(", sex=").append(sex);
        sb.append(", mobile=").append(mobile);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", email=").append(email);
        sb.append(", birthdate=").append(birthdate);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", delTime=").append(delTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}