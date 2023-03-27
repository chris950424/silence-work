package com.silence.stock.infrastructure.repository;

import com.silence.stock.domain.repository.StockRepository;
import com.silence.stock.infrastructure.dao.StockMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * StockRepositoryImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/4/23
 */
@Repository
public class StockRepositoryImpl implements StockRepository {

    @Resource
    private StockMapper mapper;

    @Override
    public boolean update(Long productId) {
        int i = mapper.update(productId);
        return i > 0;
    }
}
