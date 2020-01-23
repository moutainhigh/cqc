package com.cqc.admin.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FaqEditParam {

    @NotBlank(message = "id不能为空")
    private String id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    private Integer displayOrder;


}
