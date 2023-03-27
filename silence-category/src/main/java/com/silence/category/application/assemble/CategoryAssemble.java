package com.silence.category.application.assemble;


import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;
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
public interface CategoryAssemble {
    CategoryAssemble INSTANCE = Mappers.getMapper(CategoryAssemble.class);

    List<CategoryDto> categoriesToCategoryDtos(List<Category> categories);

    CategoryDto categoryToCategoryDto(Category category);

    CategoryAttribute attributeDtoToAttribute(CategoryAttributeDto attributeDTO);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
