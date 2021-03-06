package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户-虚拟币记录
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Data
@TableName("user_virtual_fund_record")
public class UserVirtualFundRecord implements Serializable {

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
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资产类型 1 cqc
     */
    private Integer type;

    /**
     * 方向 1 增加 2减少
     */
    private Integer direct;

    /**
     * 当前余额
     */
    private BigDecimal balance;


}
