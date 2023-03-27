package com.silence.order.domain.handler.impl;


import com.alibaba.fastjson.JSON;
import com.silence.order.domain.Handler;
import com.silence.order.domain.entity.Order;
import com.silence.order.domain.entity.Pay;
import com.silence.order.domain.repository.OrderRepository;
import com.silence.order.infrastructure.ws.WsService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@Service
public class WechatPayHandlerImpl implements Handler {

    @Resource
    OrderRepository repository;

    @Resource
    WsService wsService;

    @Override
    public String getType() {
        return "pay";
    }


    @Override
    public void handler(String msg) {
        Pay pay = JSON.parseObject(msg, Pay.class);
        Order order = new Order();
        order.setTransactionId(pay.getTransactionId());
        order.setEndTime(pay.getEndTime());
        order.setOrderNo(pay.getOrderNo());
        order.setStatus(pay.getStatus());
        repository.save(order);
        wsService.send(pay.getAttach().getUserId(),String.valueOf(order.getStatus()));
    }
}

