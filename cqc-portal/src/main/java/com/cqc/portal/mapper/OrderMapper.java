package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
     * @param orderId
     * @param userId
     * @return
     */
    @Update("update order set user_id = #{userId}, status = 1, buy_time =#{buyTime}, commission= #{commission}" +
            "where id = #{orderId} and status = 0")
    int buyOrder(String orderId, String userId, Date buyTime, BigDecimal commission);

    @Select("select * from order where status = 0 order by create_time desc limit 1")
    Order selectNewOrder();
}
