package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 提现记录
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
@Data
@TableName("user_withdraw")
public class UserWithdraw implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 银行logo
     */
    private String bankLogo;

    /**
     * 方式：1-人工转账 2-系统转账
     */
    private Integer type;

    /**
     * 提现费用
     */
    private BigDecimal cost;

    /**
     * 状态：0-转账中 1-成功 2-失败
     */
    private Integer status;

    /**
     * 第三方转账单号
     */
    private String thirdTradeNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 到账时间
     */
    private Date successTime;


}
