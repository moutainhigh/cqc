package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户-虚拟币记录
 * </p>
 *
 * @author ${author}
 * @since 2020-01-19
 */

@Data
@TableName("user_fund_record")
public class UserFundRecord implements Serializable {

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
     * 业务类型 1-任务佣金
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

    private String remark;

    private Date createTime;

}
