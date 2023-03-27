package com.silence.order.infrastructure.dao;

import com.silence.order.infrastructure.repository.DO.OrderDO;

import java.util.List;

/**
 * CategoryMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface OrderMapper {


    int insertOrder(OrderDO orderDO);

    OrderDO selectOrderByOrderNo(Long orderNo);

    int updateOrder(OrderDO orderDO);

    List<OrderDO> selectAllOrder(int status);

    int updateBatch(List<OrderDO> list);

    int insertOrderBath(List<OrderDO> list);
}
