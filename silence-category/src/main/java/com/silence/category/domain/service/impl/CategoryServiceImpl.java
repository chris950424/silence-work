package com.silence.category.domain.service.impl;

import com.silence.category.domain.entity.Category;
import com.silence.category.domain.entity.CategoryAttribute;
import com.silence.category.domain.repository.CategoryRepository;
import com.silence.category.domain.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Resource
    private CategoryRepository repository;

    @Override
    public List<Category> categoryList() {
        return repository.find();
    }

    @Override
    public Category categoryById(Long id) {
        return repository.find(id);
    }

    @Override
    public String updateAttribute(CategoryAttribute categoryAttributeVal) {
        return repository.saveAttribute(categoryAttributeVal);
    }

    @Override
    public String addAttribute(CategoryAttribute categoryAttributeVal) {
        return repository.saveAttribute(categoryAttributeVal);
    }

    @Override
    public String addCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public String updateCategory(Category category) {
        return repository.save(category);
    }
}
