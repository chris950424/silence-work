package com.silence.stock.ui.dubbo;


import com.silence.api.StockService;
import com.silence.stock.application.service.StockApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CategoryProvider
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class StockProvider implements StockService {

    @Resource
    private StockApplicationService applicationService;

    @Override
    public Boolean updateStockByProductId(Long productId) {
        return applicationService.updateStockByProductId(productId);
    }
}
