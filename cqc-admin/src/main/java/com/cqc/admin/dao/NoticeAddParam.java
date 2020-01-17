package com.cqc.admin.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoticeAddParam {

    @NotNull(message = "type不能为空")
    private Integer type;

    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

}
