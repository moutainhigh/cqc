package com.cqc.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-24
 */

@NoArgsConstructor
@Data
@TableName("rate")
public class Rate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id，硬编码 1-支付宝 2-微信
     */
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 费率
     */
    @NotNull(message = "rate不能为空")
    @Min(value = 0, message = "不能小于0")
    private BigDecimal rate;

    public Rate(String id, BigDecimal rate) {
        this.id = id;
        this.rate = rate;
    }
    public Rate(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(id, rate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
