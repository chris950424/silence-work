package com.silence.category.domain.repository;

import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;

import java.util.List;

/**
 * CategoryRepository
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface CategoryRepository {

    List<Category> find();

    Category find(Long id);

    String saveAttribute(CategoryAttribute categoryAttributeVal);

    String save(Category category);
}
