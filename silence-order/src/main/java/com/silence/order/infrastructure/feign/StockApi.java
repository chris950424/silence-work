package com.silence.order.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(name = "silence-stock")
public interface StockApi {
    @PostMapping(value = "/stock/updateStockByProductId")
    Boolean updateStockByProductId(@RequestParam("productId") Long productId);
}
