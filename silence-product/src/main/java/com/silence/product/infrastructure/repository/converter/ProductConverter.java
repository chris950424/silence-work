package com.silence.product.infrastructure.repository.converter;

import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;
import com.silence.product.infrastructure.repository.DO.ProductAttributeDO;
import com.silence.product.infrastructure.repository.DO.ProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * CategoryConverter
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Mapper
public interface ProductConverter {

    //    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    List<Product> productDOSToProducts(List<ProductDO> productDOS);

    ProductDO productToProductDo(Product product);

    ProductAttributeDO attributeToAttributeDo(ProductAttribute productAttribute);
}
