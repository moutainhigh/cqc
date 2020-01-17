package com.cqc.admin.dao;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
public class NoticeEditParam {

    @NotBlank(message = "id不能为空")
    private String id;

    private String title;

    private String content;

    public boolean isEmpty(){
        return StringUtils.isEmpty(title) && StringUtils.isEmpty(content);
    }

}
