package com.silence.order.application.assemble;

import com.silence.api.OrderDto;
import com.silence.order.domain.entity.Order;
import com.silence.order.ui.entity.command.CreateOrderCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  PermissionAssemble
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface OrderAssemble {
    OrderAssemble INSTANCE = Mappers.getMapper(OrderAssemble.class);


    Order cocToOrder(CreateOrderCommand coc);

    OrderDto orderToOrderDto(Order order);
}
