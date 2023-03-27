package com.silence.product.domain.service.impl;


import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;
import com.silence.product.domain.repository.ProductRepository;
import com.silence.product.domain.service.ProductService;
import com.silence.product.infrastructure.repository.DO.ProductDO;
import com.silence.product.infrastructure.repository.converter.ProductConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository repository;

    @Override
    public List<Product> productList() {
        return repository.find();
    }

    @Override
    public String addProduct(Product product) {
          int i = repository.save(product);
          return String.valueOf(i);
    }

    @Override
    public String addAttribute(ProductAttribute productAttribute) {
         int i  = repository.saveAttribute(productAttribute);
        return  String.valueOf(i);
    }

    @Override
    public List<Product> productsByCategoryId(String categoryId) {
        return repository.find(categoryId);
    }
}
