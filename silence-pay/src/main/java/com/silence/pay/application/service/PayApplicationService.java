package com.silence.pay.application.service;

import com.alibaba.fastjson.JSON;
import com.silence.api.PayDto;
import com.silence.pay.application.assemble.PayAssemble;
import com.silence.pay.domain.PayService;
import com.silence.pay.domain.wechat.entity.Pay;
import com.silence.pay.infrastructure.mq.KafkaService;
import com.silence.pay.infrastructure.proxy.anno.AllinPayAnno;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Map;

import static com.silence.pay.infrastructure.config.PayConfig.PAYS;

/**
 * PayApplicationService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
@Service
public class PayApplicationService {

    @Resource
    private KafkaService kafkaService;

    public String pay(PayDto payDto) {
        PayService payService = PAYS.get(payDto.getPayType());
        Pay pay = PayAssemble.INSTANCE.payDtoToPay(payDto);
        return payService.pay(pay);
    }

    public PayDto query(PayDto payDto) {
        PayService payService = PAYS.get(payDto.getPayType());
        Pay query = payService.query(payDto.getOrderNo());
        return PayAssemble.INSTANCE.payToPayDto(query);
    }


    public String callback(String payType, String sb) {
        PayService payService = PAYS.get(payType);
        System.out.println(sb);
        Pay pay = payService.callBack(sb);
        if (pay.getOrderNo() != null) {
            PayDto payDto = PayAssemble.INSTANCE.payToPayDto(pay);
            kafkaService.send("pay", JSON.toJSONString(payDto));
        }
        return pay.getResult();
    }
}
