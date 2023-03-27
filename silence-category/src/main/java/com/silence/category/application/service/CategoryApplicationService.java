package com.silence.category.application.service;

import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.category.application.assemble.CategoryAssemble;
import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;
import com.silence.category.domain.service.CategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryApplicationService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class CategoryApplicationService {
    Log log = LogFactory.getLog(CategoryApplicationService.class);

    @Resource
    private CategoryService categoryService;



    public List<CategoryDto> categoryList() {
        log.info("1111111111111111");
        List<Category> categories = categoryService.categoryList();
        return CategoryAssemble.INSTANCE.categoriesToCategoryDtos(categories);
    }

    public CategoryDto categoryById(Long id) {
        Category category =    categoryService.categoryById(id);
        return CategoryAssemble.INSTANCE.categoryToCategoryDto(category);
    }

    public String addAttribute(CategoryAttributeDto attributeDTO) {
       CategoryAttribute categoryAttributeVal =  CategoryAssemble.INSTANCE.attributeDtoToAttribute(attributeDTO);
       return   categoryService.addAttribute(categoryAttributeVal);
    }

    public String addCategory(CategoryDto categoryDto) {
       Category category =   CategoryAssemble.INSTANCE.categoryDtoToCategory(categoryDto);
        return categoryService.addCategory(category);
    }

    public String updateCategory(CategoryDto categoryDto) {
        Category category =   CategoryAssemble.INSTANCE.categoryDtoToCategory(categoryDto);
        return categoryService.updateCategory(category);
    }

    public String updateAttribute(CategoryAttributeDto attributeDTO) {
        CategoryAttribute categoryAttribute =  CategoryAssemble.INSTANCE.attributeDtoToAttribute(attributeDTO);
        return categoryService.updateAttribute(categoryAttribute);
    }

}
