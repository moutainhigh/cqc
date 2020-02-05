package com.cqc.portal.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

@Data
public class ReceiveCodeQueryParam extends PageQuery {

    private String name;

    private Integer type;

}
