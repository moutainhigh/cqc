package com.cqc.portal.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

@Data
public class OrderQuery extends PageQuery {

    private String startTimeStr;

    private String endTimeStr;

    private Integer channel;

    private Integer type;

    private Integer status;
}
