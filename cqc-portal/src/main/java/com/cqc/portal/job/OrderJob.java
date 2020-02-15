package com.cqc.portal.job;

import com.cqc.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class OrderJob {

    @Autowired
    private OrderService orderService;


    /**
     * 自动执行取消订单操作
     */
    @Scheduled(fixedRate = 120 * 1000, initialDelay = 30 * 1000)
    public void autoCancel() {
        orderService.autoCancel();
    }

}
