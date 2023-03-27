package com.silence.order.infrastructure.repository.converter;

import com.silence.order.domain.entity.Order;
import com.silence.order.infrastructure.repository.DO.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * CategoryConverter
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Mapper
public interface OrderConverter {
    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    OrderDO orderToOrderDo(Order order);

    Order orderDoToOrder(OrderDO orderDO);
}
