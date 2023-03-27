package com.silence.category.infrastructure.repository.converter;

import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;
import com.silence.category.infrastructure.repository.DO.CategoryAttributeDO;
import com.silence.category.infrastructure.repository.DO.CategoryDO;
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
public interface CategoryConverter {

    //    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);
    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);


    List<Category> categoryDosToCategories(List<CategoryDO> categoryDos);

    Category categroyDoToCategory(CategoryDO categoryDO);

    CategoryAttributeDO categoryAttributeValToCategoryAttributeDO(CategoryAttribute categoryAttributeVal);

    CategoryDO categoryToCategoryDO(Category category);
}
