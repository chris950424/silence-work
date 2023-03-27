package com.silence.product.application.service;


import com.silence.api.ProductAttributeDto;
import com.silence.api.ProductAttributeMap;
import com.silence.api.ProductDto;
import com.silence.product.application.assemble.ProductAssemble;
import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;
import com.silence.product.domain.service.ProductService;
import com.silence.product.ui.entity.query.ProductListQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CategoryApplicationService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class ProductApplicationService {

    @Resource
    private ProductService productService;

    public List<ProductDto> productList() {
        List<Product> products = productService.productList();
        return ProductAssemble.INSTANCE.productsToProductDto(products);
    }

    public String addProduct(ProductDto productDto) {
        Product product = ProductAssemble.INSTANCE.productDtoToProduct(productDto);
        return productService.addProduct(product);
    }

    public String addAttribute(ProductAttributeDto attributeDTO) {
        ProductAttribute productAttribute = ProductAssemble.INSTANCE.atributeDTOToProductAttribute(attributeDTO);
        return productService.addAttribute(productAttribute);
    }

    public List<ProductDto> productsByCategoryId(ProductListQuery plq) {
        List<Product> products = productService.productsByCategoryId(plq.getCategoryId());
        List<ProductDto> productDtos = ProductAssemble.INSTANCE.productsToProductDto(products);
        productDtos.forEach(a -> {
            List<ProductAttributeDto> attributes = a.getAttributes();
            Map<String, List<ProductAttributeDto>> collect = attributes.stream().collect(Collectors.groupingBy(ProductAttributeDto::getType));
            List<ProductAttributeMap> list = new ArrayList<>();
            collect.forEach((c, d) -> {
                ProductAttributeMap map = new ProductAttributeMap();
                map.setKey(c);
                map.setAttributeDtos(d);
                list.add(map);
            });
            a.setAttributeMap(list);
        });
        return productDtos;
    }
}
