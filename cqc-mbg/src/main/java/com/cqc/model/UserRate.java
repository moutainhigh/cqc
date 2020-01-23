package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户费率
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@NoArgsConstructor
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
    @NotNull(message = "channel不能为空")
    private Integer channel;

    /**
     * 费率
     */
    @NotNull(message = "rate不能为空")
    @Min(value = 0, message = "最小值0")
    private BigDecimal rate;


    public UserRate(String userId, Integer channel, BigDecimal rate) {
        this.userId = userId;
        this.channel = channel;
        this.rate = rate;
    }
}
