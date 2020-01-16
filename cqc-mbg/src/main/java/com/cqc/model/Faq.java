package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 常见问题
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
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
    private LocalDateTime createTime;

    /**
     * 修改者 后台用户账号
     */
    private String editor;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 状态 1-有效 0无效
     */
    private Boolean status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Faq{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", author=" + author +
        ", createTime=" + createTime +
        ", editor=" + editor +
        ", modifyTime=" + modifyTime +
        ", status=" + status +
        "}";
    }
}
