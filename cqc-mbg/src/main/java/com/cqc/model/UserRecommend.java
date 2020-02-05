package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-02-03
 */

@Data
@TableName("user_recommend")
public class UserRecommend implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 1表示只有1级上级 2表示有2级上级，最大5  注册的时候有程序决定
     */
    private Integer level;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 上级用户id
     */
    private String refUserId;

    /**
     * 上级用户账号
     */
    private String refUserAccount;

}
