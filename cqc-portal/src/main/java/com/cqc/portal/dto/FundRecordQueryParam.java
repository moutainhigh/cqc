package com.cqc.portal.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

@Data
public class FundRecordQueryParam extends PageQuery {

    private String startTimeStr;

    private String endTimeStr;

    private Integer type;

}
