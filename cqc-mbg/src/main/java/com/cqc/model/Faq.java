package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 常见问题
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */

@Data
@TableName("faq")
public class Faq implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID, 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建者 后台用户账号
     */
    private String author;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者 后台用户账号
     */
    private String editor;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 状态 1-有效 0无效
     */
    private Integer status;



}
