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
 * @since 2020-01-17
 */

@Data
@TableName("invite_code")
public class InviteCode implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 邀请码，唯一
     */
    private String inviteCode;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 过期时间
     */
    private Integer expire;

    /**
     * 创建时间
     */
    private Date createTime;


}
