package com.silence.order.infrastructure.facade.impl;

import com.silence.api.PayDto;
import com.silence.api.PayService;
import com.silence.api.StockService;
import com.silence.order.infrastructure.facade.PayFacade;
import com.silence.order.infrastructure.facade.StockFacade;
import com.silence.order.infrastructure.feign.StockApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CategoryFacadeImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class StockFacadeImpl implements StockFacade {

    @Resource
    private StockApi stockService;

    @Override
    public Boolean updateStockByProductId(Long productId) {
        return stockService.updateStockByProductId(productId);
    }
}
