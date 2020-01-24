package com.cqc.portal.api.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-01-24
 */
@TableName("rate")
public class Rate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id，硬编码 1-支付宝 2-微信
     */
    private String id;

    /**
     * 费率
     */
    private BigDecimal rate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
        "id=" + id +
        ", rate=" + rate +
        "}";
    }
}
