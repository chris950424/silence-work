package com.silence.product.ui.dubbo;


import com.silence.api.ProductAttributeDto;
import com.silence.api.ProductDto;
import com.silence.api.ProductService;
import com.silence.product.application.service.ProductApplicationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryProvider
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class ProductProvider implements ProductService {

    @Resource
    private ProductApplicationService applicationService;


    @Override
    public List<ProductDto> productList() {
        return applicationService.productList();
    }

    @Override
    public String addProduct(ProductDto productDto) {
        return applicationService.addProduct(productDto);
    }

    @Override
    public String addAttribute(ProductAttributeDto attributeDTO) {
        return applicationService.addAttribute(attributeDTO);
    }
}
