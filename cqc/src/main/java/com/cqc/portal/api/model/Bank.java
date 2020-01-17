package com.cqc.portal.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 银行
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Bank{" +
        "id=" + id +
        ", name=" + name +
        ", logo=" + logo +
        "}";
    }
}
