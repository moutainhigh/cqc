package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户金额
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */

@Data
@TableName("user_fund")
public class UserFund implements Serializable {

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
     * 余额
     */
    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * 冻结金额
     */
    private BigDecimal freezeBalance = BigDecimal.ZERO;

    /**
     * 可用金额
     */
    private BigDecimal availableBalance = BigDecimal.ZERO;

    public UserFund() {
    }

    public UserFund(String userId) {
        this.userId = userId;
    }
}
