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
     * 用户账号
     */
    private String account;

    /**
     * 授权渠道 1-支付宝 2-微信
     */
    private Integer channel;

    /**
     * 名称
     */
    private String receiveName;

    /**
     * 收款码
     */
    private String code;

    /**
     * 二维码图片链接
     */
    private String codeImg;

    /**
     * 状态 1-启动 0关闭
     */
    private Integer status;


}
