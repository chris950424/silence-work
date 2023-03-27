package com.silence.pay.ui.dubbo;

import com.silence.api.PayDto;
import com.silence.api.PayService;
import com.silence.pay.application.service.PayApplicationService;

import javax.annotation.Resource;

/**
 *  PayProvider
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
public class PayProvider implements PayService {

    @Resource
    private PayApplicationService applicationService;

    @Override
    public String pay(PayDto payDto) {
        return applicationService.pay(payDto);
    }

    @Override
    public PayDto query(PayDto payDto) {
        return applicationService.query(payDto);
    }
}
