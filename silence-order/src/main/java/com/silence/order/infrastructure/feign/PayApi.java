package com.silence.order.infrastructure.feign;

import com.silence.api.PayDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Administrator
 */
@FeignClient(name = "silence-pay")
public interface PayApi {

    @PostMapping(value = "/pay/pay")
    String pay(PayDto payDto);
    @PostMapping(value = "/pay/query")
    PayDto query(PayDto payDto);


}
