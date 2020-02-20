package com.cqc.portal;

import com.cqc.common.utils.OrderUtils;
import com.cqc.model.Order;
import com.cqc.model.OrderPublish;
import com.cqc.portal.service.OrderPublishService;
import com.cqc.portal.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PortalApplication.class})
public class OrderTest {

    @Autowired
    private OrderPublishService orderPublishService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testAddOrderPublish() {

        List<Order> list = orderService.list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }


        for (Order order : list) {
            Order publish = new Order();
            publish.setId(order.getId());
            publish.setOrderSn(OrderUtils.generatePublishOrderSn());

            orderService.updateById(publish);
        }

    }


    @Test
    public void testAutoCancelOrder() {
        Order order = orderService.getById("3da6a6677c1d34e0dabc36bfb811e6a9");

        orderService.cancel(order);

    }
}
