package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

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
    private Boolean type;

    /**
     * 状态：0-无效 1-通过
     */
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Notice{" +
        "id=" + id +
        ", type=" + type +
        ", status=" + status +
        ", title=" + title +
        ", content=" + content +
        ", author=" + author +
        ", createTime=" + createTime +
        "}";
    }
}
