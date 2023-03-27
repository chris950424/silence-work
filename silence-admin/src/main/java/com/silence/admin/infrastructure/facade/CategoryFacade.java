package com.silence.admin.infrastructure.facade;

import com.silence.admin.ui.entity.command.AddAttributeCommand;
import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;

import java.util.List;

/**
 *  CategoryFacade
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */

public interface CategoryFacade {

    List<CategoryDto> categoryList();

    CategoryDto categoryById(Long id);

    String addAttribute(CategoryAttributeDto attributeDTO);

    String addCategory(CategoryDto categoryDto);

    String  updateCategory(CategoryDto categoryDto);

    String updateAttribute(CategoryAttributeDto attributeDTO);
}
