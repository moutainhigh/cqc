package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.Order;
import com.cqc.model.ReceiveCode;
import com.cqc.model.User;
import com.cqc.portal.dto.resp.CityHotDataDto;

import java.math.BigDecimal;
import java.util.List;

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
     * @param user
     * @param list
     */
    void autoOrder(User user, List<ReceiveCode> list);

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

    /**
     * 以城市排行统计订单数量
     *
     * @return
     */
    List<CityHotDataDto> cityHot();
}
