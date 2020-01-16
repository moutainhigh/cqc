package com.cqc.common.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author wanglz
 */
@Data
public class PageQuery {

    @Min(value = 1, message = "pageNum最小值1")
    @ApiModelProperty(value = "当前页",example = "1")
    private int pageNum = 1;


    @Max(value = 1000, message = "pageSize最大值1000")
    @ApiModelProperty(value = "每页条数",example = "10")
    private int pageSize = 10;



}
