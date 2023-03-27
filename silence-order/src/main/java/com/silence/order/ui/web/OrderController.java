package com.silence.order.ui.web;

import com.silence.api.OrderDto;
import com.silence.order.application.service.OrderApplicationService;
import com.silence.order.infrastructure.base.ApiResponse;
import com.silence.order.ui.entity.command.CreateOrderCommand;
import com.silence.order.ui.entity.command.PayCommand;
import com.silence.order.ui.entity.query.OrderQuery;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CategoryController
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    private OrderApplicationService applicationService;

    @PostMapping(value = "/createOrder")
    public ApiResponse<String> createOrder(@RequestBody CreateOrderCommand coc) {
        String orderNo = applicationService.createOrder(coc);
        return ApiResponse.success("成功",orderNo);
    }
    @PostMapping(value = "/getOrderByOrderNo")
    public ApiResponse<OrderDto> getOrderByOrderNo(@RequestBody OrderQuery oq) {
        OrderDto  orderDto = applicationService.getOrderByOrderNo(oq.getOrderNo());
        return ApiResponse.success("成功",orderDto);
    }


    @PostMapping(value = "/pay")
    public ApiResponse<String> getOrderByOrderNo(@RequestBody PayCommand pc) {
        String  string = applicationService.pay(pc);
        return ApiResponse.success("成功",string);
    }

}
