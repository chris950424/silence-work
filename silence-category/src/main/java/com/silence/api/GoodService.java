package com.silence.api;

import java.util.List;

/**
 *  CategoryService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface GoodService {

     List<CategoryDto> categoryList();

     CategoryDto categoryById(Long id);


     String addAttribute(CategoryAttributeDto attributeDTO);

     String addCategory(CategoryDto categoryDto);

     String updateCategory(CategoryDto categoryDto);

     String updateAttribute(CategoryAttributeDto attributeDTO);

}
