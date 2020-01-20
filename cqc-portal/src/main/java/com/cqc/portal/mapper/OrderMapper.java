package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.Order;
import org.apache.ibatis.annotations.Param;
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

    int buyOrder(@Param("orderId") String orderId, @Param("userId")String userId,
                 @Param("buyTime")Date buyTime, @Param("commission")BigDecimal commission);

    Order selectNewOrder(@Param("balance") BigDecimal balance);

    /**
     * 查询待入cqc收益
     * @param userId
     * @return
     */
    @Select("select ifnull(sum(o.income), 0) from `order` as o where user_id = #{userId} and `status` = 0")
    BigDecimal waitPayIncome(String userId);
}
