package com.silence.order.infrastructure.facade;

import com.silence.api.PayDto;

/**
 *  CategoryFacade
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface StockFacade {

    /**
     * @param productId
     * @return
     */
    Boolean updateStockByProductId(Long productId);

}
