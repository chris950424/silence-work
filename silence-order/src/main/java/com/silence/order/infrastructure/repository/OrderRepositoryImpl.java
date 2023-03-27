package com.silence.order.infrastructure.repository;

import com.silence.order.domain.entity.Order;
import com.silence.order.domain.repository.OrderRepository;
import com.silence.order.infrastructure.dao.OrderMapper;
import com.silence.order.infrastructure.repository.DO.OrderDO;
import com.silence.order.infrastructure.repository.converter.OrderConverter;
import com.silence.order.infrastructure.util.uid.UidGenerator;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * CategoryRepositoryImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Resource
    OrderMapper mapper;

    @Resource
    UidGenerator generator;

    @Override
    public String save(Order order) {
        OrderDO orderDO = OrderConverter.INSTANCE.orderToOrderDo(order);
        if (orderDO.getOrderNo() != null) {
            int i = mapper.updateOrder(orderDO);
            return String.valueOf(i);
        }
        long uid = generator.getUID();
        orderDO.setOrderNo(uid);
        int i = mapper.insertOrder(orderDO);
        return String.valueOf(uid);
    }


    @Override
    public Order find(Long orderNo) {
        OrderDO orderDO = mapper.selectOrderByOrderNo(orderNo);
        return OrderConverter.INSTANCE.orderDoToOrder(orderDO);
    }
}
