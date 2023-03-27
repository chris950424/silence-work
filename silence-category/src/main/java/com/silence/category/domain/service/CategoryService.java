package com.silence.category.domain.service;

import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;

import java.util.List;

/**
 * CategoryService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface CategoryService {

    /**
     * 分类列表
     *
     * @return
     */
    List<Category> categoryList();

    Category categoryById(Long id);

    String addAttribute(CategoryAttribute categoryAttributeVal);

    String addCategory(Category category);

    String updateCategory(Category category);

    String updateAttribute(CategoryAttribute categoryAttribute);
}
