package com.silence.api;


import java.util.List;

/**
 * ProductService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface ProductService {
    List<ProductDto> productList();

    String addProduct(ProductDto productDto);

    String addAttribute(ProductAttributeDto attributeDTO);
}
