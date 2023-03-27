package com.silence.product.domain.repository;


import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;

import java.util.List;

/**
 * CategoryRepository
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface ProductRepository {


    List<Product> find();

    List<Product> find(String categoryId);


    int save(Product product);

    int saveAttribute(ProductAttribute productAttribute);
}
