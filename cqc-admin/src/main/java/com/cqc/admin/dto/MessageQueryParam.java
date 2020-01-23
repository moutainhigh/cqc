package com.cqc.admin.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

@Data
public class MessageQueryParam extends PageQuery {

    private Integer type;

}
