package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户-虚拟币资产
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Data
@TableName("user_virtual_fund")
public class UserVirtualFund implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID， 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 资产类型 1-cqc
     */
    private Integer type;

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

    public UserVirtualFund() {
    }

    public UserVirtualFund(String userId, Integer type) {
        this.userId = userId;
        this.type = type;
    }
}
