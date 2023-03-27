package com.silence.category.infrastructure.dao;


import com.silence.category.infrastructure.repository.DO.CategoryAttributeDO;
import com.silence.category.infrastructure.repository.DO.CategoryDO;

import java.util.List;

/**
 * CategoryMapper
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public interface CategoryMapper {


    /**
     * 查询所有分类
     *
     * @return
     */
    List<CategoryDO> selectCategoryList();

    CategoryDO selectCategoryById(Long id);

    int addAttribute(CategoryAttributeDO attributeDO);

    int updateCategory(CategoryDO dO);

    int insertCategory(CategoryDO dO);

    int updateAttribute(CategoryAttributeDO attributeDO);
}
