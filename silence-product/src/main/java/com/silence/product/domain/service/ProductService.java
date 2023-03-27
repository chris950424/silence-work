package com.silence.product.domain.service;


import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;

import java.util.List;

/**
 * CategoryService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface ProductService {


    List<Product> productList();

    String addProduct(Product product);

    String addAttribute(ProductAttribute productAttribute);

    List<Product> productsByCategoryId(String categoryId);
}
