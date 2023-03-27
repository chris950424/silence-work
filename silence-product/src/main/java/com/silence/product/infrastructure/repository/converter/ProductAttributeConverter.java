package com.silence.product.infrastructure.repository.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  CategoryConverter
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Mapper
public interface ProductAttributeConverter {

    ProductAttributeConverter INSTANCE = Mappers.getMapper(ProductAttributeConverter.class);

}
