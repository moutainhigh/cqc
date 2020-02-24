package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.OrderPublish;
import com.cqc.portal.mapper.OrderPublishMapper;
import com.cqc.portal.service.OrderPublishService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-15
 */
@Service
public class OrderPublishServiceImpl extends ServiceImpl<OrderPublishMapper, OrderPublish> implements OrderPublishService {



    @Override
    public OrderPublish selectNewOrder(BigDecimal balance, Set<Integer> channelSet) {

        QueryWrapper<OrderPublish> wrapper = new QueryWrapper<OrderPublish>()
                .in("channel", channelSet).le("amount", balance)
                .orderByDesc("create_time");


        int count = super.count(wrapper);
        if (count <= 0) {
            return null;
        }
        int random = new Random().nextInt(count);
        wrapper = wrapper.last("limit "+random+"  , 1");

        return super.getOne(wrapper);
    }


}
