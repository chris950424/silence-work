package com.silence.admin.infrastructure.facade.impl;

import com.silence.admin.infrastructure.facade.CategoryFacade;
import com.silence.admin.infrastructure.feign.CategoryApi;
import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryFacadeImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class CategoryFacadeImpl implements CategoryFacade {

    Logger logger = LoggerFactory.getLogger(CategoryFacadeImpl.class);

    @Resource
    private CategoryApi categoryService;


    @Override
    public List<CategoryDto> categoryList() {
        List<CategoryDto> categoryDtos = categoryService.categoryList();
        return categoryDtos;
    }

    @Override
    public CategoryDto categoryById(Long id) {
        return categoryService.categoryById(id);
    }

    @Override
    public String addAttribute(CategoryAttributeDto attributeDTO) {
        return categoryService.addAttribute(attributeDTO);
    }

    @Override
    public String addCategory(CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @Override
    public String updateCategory(CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @Override
    public String updateAttribute(CategoryAttributeDto attributeDTO) {
        return categoryService.updateAttribute(attributeDTO);
    }
}
