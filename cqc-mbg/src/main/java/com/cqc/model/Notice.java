package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 公告
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@TableName("notice")
public class Notice implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键，ID
     */
    private String id;

    /**
     * 1-首页弹窗 2-慢放 3-取款弹窗
     */
    private Integer type;

    /**
     * 状态：0-无效 1-通过
     */
    private Integer status;

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



}
