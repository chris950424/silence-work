package com.silence.stock.domain.service.impl;

import com.silence.stock.domain.repository.StockRepository;
import com.silence.stock.domain.service.StockService;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * StockServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/4/23
 */
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStockByProductId(Long productId) {
        String xid = RootContext.getXID();
        System.out.println(xid);
        return repository.update(productId);
    }
}
