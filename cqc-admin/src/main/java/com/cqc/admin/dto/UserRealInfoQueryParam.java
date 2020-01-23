package com.cqc.admin.dto;

import com.cqc.common.api.PageQuery;
import lombok.Data;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Data
public class UserRealInfoQueryParam extends PageQuery {


    private Integer status;

    private int type = 1;

}
