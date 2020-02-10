package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-02-10
 */

@Data
@TableName("pdd_account")
public class PddAccount implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 拼多多账号类型 1-商家 2个人
     */
    private Integer type;

    /**
     * 拼多多账号，或者手机号码
     */
    private String pddAccount;

    /**
     * 拼多多
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;


}
