package com.silence.order.domain.service;


import com.silence.order.domain.entity.Order;

/**
 * CategoryService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface OrderService {


    String createOrder(Order order);

    Order getOrderByOrderNo(Long orderNo);
}
