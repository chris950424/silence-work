package com.silence.stock.application.service;

import com.silence.stock.domain.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockApplicationService {

    @Resource
    private StockService stockService;

    public Boolean updateStockByProductId(Long productId) {
        return stockService.updateStockByProductId(productId);
    }
}
