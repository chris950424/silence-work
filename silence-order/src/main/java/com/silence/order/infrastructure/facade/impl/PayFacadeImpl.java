package com.silence.order.infrastructure.facade.impl;

import com.silence.api.PayDto;
import com.silence.api.PayService;
import com.silence.order.infrastructure.facade.PayFacade;
import com.silence.order.infrastructure.feign.PayApi;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CategoryFacadeImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Component
public class PayFacadeImpl implements PayFacade {

    @Resource
    private PayApi payService;

    @Override
    public String pay(PayDto payDto) {
        return payService.pay(payDto);
    }

    @Override
    public PayDto query(PayDto payDto) {
        return payService.query(payDto);
    }
}
