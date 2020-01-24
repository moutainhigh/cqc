package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
@Data
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键，ID
     */
    private String id;

    /**
     * 1-佣金加成 2-全天佣金加成 3-重要通知 4-夜间佣金加成 5-全天加成 6-夜间活动 7-温馨提示 8-爆单提醒 9-同城派单提醒 10-充值注意事项 10-信用积分说明
     */
    private Integer type;

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
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态：0-无效 1-通过
     */
    private Integer status;



}
