package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.User;
import org.apache.ibatis.annotations.Update;


/**
 * @author ZZT
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 更改自动抢单状态
     * @param id
     * @param autoOrderStatus
     * @return
     */

    @Update("update user set auto_order_status = #{autoOrderStatus} where id = #{id}")
    int updateAutoOrder(String id, int autoOrderStatus);

}