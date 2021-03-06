package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.Order;
import com.cqc.portal.dto.resp.CityHotDataDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * @param order
     * @return
     */
    int buyOrder(Order order);

    /**
     *
     * @param balance
     * @param channelSet
     * @return
     */
    Order selectNewOrder(@Param("balance") BigDecimal balance, @Param("channelSet") Set<Integer> channelSet);

    /**
     * 查询待入cqc收益
     * @param userId
     * @return
     */
    @Select("select ifnull(sum(o.amount), 0) from `order` as o where user_id = #{userId} and `status` = 0")
    BigDecimal waitPayIncome(String userId);

    /**
     * 以城市排行统计订单数量
     * @return
     */
    List<CityHotDataDto> cityHot();

    /**
     * 查询超时未支付的订单
     * @return
     */
    List<Order> payTimeOutList();

    /**
     *
     * @param receiveId
     * @param amount
     * @return
     */
    @Select("select count(1) from `order` as o where receive_code_id = #{receiveId} and `amount` = #{amount} and status = 0")
    Integer countByReceiveId(String receiveId, BigDecimal amount);

}
