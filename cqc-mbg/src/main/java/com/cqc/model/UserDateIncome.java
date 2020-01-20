package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-20
 */

@Data
@TableName("user_date_income")
public class UserDateIncome implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id，联合主键
     */
    private String userId;

    /**
     * 日期，联合主键
     */
    private String date;

    /**
     * 推荐用户id
     */
    private String refUserId;

    /**
     * 推荐用户账号
     */
    private String refUserAccount;

    /**
     * 当日收益
     */
    private BigDecimal income;


}
