package com.silence.product.application.assemble;


import com.silence.api.ProductAttributeDto;
import com.silence.api.ProductDto;
import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 *  PermissionAssemble
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface ProductAssemble {
    ProductAssemble INSTANCE = Mappers.getMapper(ProductAssemble.class);


    List<ProductDto> productsToProductDto(List<Product> products);

    Product productDtoToProduct(ProductDto productDto);

    ProductAttribute atributeDTOToProductAttribute(ProductAttributeDto attributeDTO);
}
