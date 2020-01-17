package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 银行
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Data
@TableName("bank")
public class Bank implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID，主键
     */
    private String id;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 银行logo
     */
    private String logo;

    public Bank() {
    }

    public Bank(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
}
