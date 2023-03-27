package com.silence.order.application.assemble;

import com.silence.api.OrderDto;
import com.silence.api.PayDto;
import com.silence.order.domain.entity.Order;
import com.silence.order.ui.entity.command.CreateOrderCommand;
import com.silence.order.ui.entity.command.PayCommand;
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
public interface PayAssemble {
    PayAssemble INSTANCE = Mappers.getMapper(PayAssemble.class);

    PayDto pcToPayDto(PayCommand pc);
}
