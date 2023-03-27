package com.silence.product.infrastructure.dao;

import com.silence.product.infrastructure.repository.DO.ProductAttributeDO;
import com.silence.product.infrastructure.repository.DO.ProductDO;

import java.util.List;

/**
 * CategoryMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface ProductMapper {

    List<ProductDO> selectProductList();

    int insertProduct(ProductDO productDO);

    int insertAttribute(ProductAttributeDO attributeDO);

    List<ProductDO> selectProductListByCategoryId(String categoryId);
}
