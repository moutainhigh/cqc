package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户费率
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Data
@TableName("user_rate")
public class UserRate implements Serializable {

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
     * 1 支付宝 2微信
     */
    private Integer channel;

    /**
     * 费率
     */
    private BigDecimal rate;



}
