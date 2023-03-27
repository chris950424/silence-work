package com.silence.order.domain.repository;


import com.silence.order.domain.entity.Order;

/**
 * CategoryRepository
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface OrderRepository {

    String save(Order order);

    Order find(Long orderNo);
}
