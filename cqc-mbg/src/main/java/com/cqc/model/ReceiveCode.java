package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */

@Data
@TableName("receive_code")
public class ReceiveCode implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 收款码
     */
    private String code;

    /**
     * 1 支付宝 2微信
     */
    private Integer type;

    /**
     * 状态 1-启动 0关闭
     */
    private Integer status;


}
