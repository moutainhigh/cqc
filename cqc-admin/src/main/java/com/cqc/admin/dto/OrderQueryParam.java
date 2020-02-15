package com.cqc.admin.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

@Data
public class OrderQueryParam extends PageQuery {

    private Integer status;

    private String orderSn;

}
