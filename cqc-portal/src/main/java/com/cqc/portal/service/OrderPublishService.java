package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.OrderPublish;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-15
 */
public interface OrderPublishService extends IService<OrderPublish> {

    /**
     * 查询放单中的订单  随机取一条
     * @param balance
     * @param channelSet
     * @return
     */
    OrderPublish selectNewOrder(BigDecimal balance, Set<Integer> channelSet);

}
