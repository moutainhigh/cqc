package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户实名信息
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Data
@TableName("user_real_info")
public class UserRealInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 认证类型 1-身份证 2-护照
     */
    private Boolean type;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 证件号码
     */
    private String idNumber;

    /**
     * 证件照1
     */
    private String img1;

    /**
     * 证件照2
     */
    private String img2;

    private String qq;

    private String email;

    private String telephone;


    /**
     * 状态 -1删除 0 禁用 1 正常 
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;



}
