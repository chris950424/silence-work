package com.silence.category.infrastructure.repository.converter;

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
public interface CategoryAttributeConverter {

    CategoryAttributeConverter INSTANCE = Mappers.getMapper(CategoryAttributeConverter.class);

}
