package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.Order;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
public interface OrderMapper extends BaseMapper<Order> {


    /**
     * 抢单
     * @param orderSn
     * @param userId
     * @return
     */
    @Update("update order set user_id = #{userId}, status = 1 where order_sn = #{orderSn} and status = 0")
    int buyOrder(String orderSn, String userId);

}
