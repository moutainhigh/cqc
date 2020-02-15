package com.cqc.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqc.model.Order;
import com.cqc.model.OrderPublish;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-02-15
 */
public interface OrderPublishMapper extends BaseMapper<OrderPublish> {


    /**
     *
     * @param balance
     * @param channelSet
     * @return
     */
    OrderPublish selectNewOrder(@Param("balance") BigDecimal balance, @Param("channelSet") Set<Integer> channelSet);

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(String id, int status);


    /**
     * 根据订单号查询
     * @param orderSn
     * @return
     */
    OrderPublish selectByOrderSn(String orderSn);

}
