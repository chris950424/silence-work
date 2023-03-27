package com.silence.order.application.service;

import com.silence.api.OrderDto;
import com.silence.api.PayAttachDto;
import com.silence.api.PayDto;
import com.silence.api.UserDto;
import com.silence.order.application.assemble.OrderAssemble;
import com.silence.order.domain.entity.Order;
import com.silence.order.domain.service.OrderService;
import com.silence.order.infrastructure.facade.PayFacade;
import com.silence.order.infrastructure.facade.StockFacade;
import com.silence.order.ui.entity.command.CreateOrderCommand;
import com.silence.order.ui.entity.command.PayCommand;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * OrderApplicationService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class OrderApplicationService {

    @Resource
    private OrderService service;


    @Resource
    PayFacade payFacade;

    @Resource
    StockFacade stockFacade;


    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderCommand coc) {
        Order order = OrderAssemble.INSTANCE.cocToOrder(coc);

        stockFacade.updateStockByProductId(order.getProductId());

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDto userDto = ((UserDto) authentication.getPrincipal());
//        order.setUserId(Long.parseLong(userDto.getId()));
        return service.createOrder(order);
    }

    public OrderDto getOrderByOrderNo(String orderNo) {
        Order order = service.getOrderByOrderNo(Long.parseLong(orderNo));
        return OrderAssemble.INSTANCE.orderToOrderDto(order);
    }

    public String pay(PayCommand pc) {
        OrderDto orderByOrderNo = getOrderByOrderNo(pc.getOrderNo());

        PayDto payDto = new PayDto();
        payDto.setPayType(pc.getPayType());
        payDto.setOrderNo(orderByOrderNo.getOrderNo());
        payDto.setAmount(orderByOrderNo.getAmount());
        PayAttachDto payAttachDto = new PayAttachDto();
        payAttachDto.setUserId(orderByOrderNo.getUserId());
        payDto.setAttach(payAttachDto);
        return payFacade.pay(payDto);
    }
}
