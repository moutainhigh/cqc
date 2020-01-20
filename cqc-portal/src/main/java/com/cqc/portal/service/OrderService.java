package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.Order;

import java.math.BigDecimal;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
public interface OrderService extends IService<Order> {

    /**
     * 自动抢单
     * @param userId
     */
    void autoOrder(String userId);

    /**
     * 确认付款
     * @param id
     * @return
     */
    boolean confirmPay(String userId, String id);

    /**
     * 查询待入cqc
     * @param userId
     * @return
     */
    BigDecimal getWaitPayIncome(String userId);

}
