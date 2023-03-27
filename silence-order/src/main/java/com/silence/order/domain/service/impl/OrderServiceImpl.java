package com.silence.order.domain.service.impl;


//import com.baomidou.dynamic.datasource.annotation.DS;
//import com.baomidou.dynamic.datasource.annotation.DS;
import com.silence.order.domain.entity.Order;
import com.silence.order.domain.repository.OrderRepository;
import com.silence.order.domain.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository repository;

    @Override
//    @DS("sharding")
    public String createOrder(Order order) {
        int i = 1/0;
        List<BigDecimal> attributes = order.getAttributes();
        BigDecimal sum = new BigDecimal(0);
        for (BigDecimal b : attributes) {
            sum = sum.add(b);
        }
        order.setAmount(sum);
        return repository.save(order);
    }

    @Override
//    @DS("sharding")
    public Order getOrderByOrderNo(Long orderNo) {
        return repository.find(orderNo);
    }
}
