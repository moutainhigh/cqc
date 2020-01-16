package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 消息
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
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
    private Boolean type;

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
    private LocalDateTime createTime;

    /**
     * 状态：0-无效 1-通过
     */
    private Boolean status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
        "id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", content=" + content +
        ", author=" + author +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
