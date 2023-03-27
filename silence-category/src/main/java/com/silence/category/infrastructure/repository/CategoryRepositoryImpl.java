package com.silence.category.infrastructure.repository;

import com.silence.category.domain.entity.CategoryAttribute;
import com.silence.category.infrastructure.dao.CategoryMapper;
import com.silence.category.domain.entity.Category;
import com.silence.category.domain.repository.CategoryRepository;
import com.silence.category.infrastructure.repository.DO.CategoryAttributeDO;
import com.silence.category.infrastructure.repository.DO.CategoryDO;
import com.silence.category.infrastructure.repository.converter.CategoryConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

/**
 * CategoryRepositoryImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> find() {
        List<CategoryDO> categoryDos = categoryMapper.selectCategoryList();
        return CategoryConverter.INSTANCE.categoryDosToCategories(categoryDos);
    }

    @Override
    public Category find(Long id) {
        CategoryDO categoryDO = categoryMapper.selectCategoryById(id);
        return CategoryConverter.INSTANCE.categroyDoToCategory(categoryDO);
    }

    @Override
    public String saveAttribute(CategoryAttribute categoryAttributeVal) {
        CategoryAttributeDO attributeDO = CategoryConverter.INSTANCE.categoryAttributeValToCategoryAttributeDO(categoryAttributeVal);
        if (attributeDO.getAttributeId() != null) {
            int i = categoryMapper.updateAttribute(attributeDO);
            return String.valueOf(i);
        }
        int i = categoryMapper.addAttribute(attributeDO);
        return String.valueOf(i);
    }

    @Override
    public String save(Category category) {
        CategoryDO dO = CategoryConverter.INSTANCE.categoryToCategoryDO(category);
        if (dO.getId() != null) {
            int i = categoryMapper.updateCategory(dO);
            return String.valueOf(i);
        }
        if (dO.getPid() == null) {
            dO.setPid(0L);
        }
        int i = categoryMapper.insertCategory(dO);
        return String.valueOf(i);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 注册 JDBC 驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 打开连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://106.13.192.32:3306/user_db?useUnicode=true", "silence", "Mypass@123456");
        // 执行查询
        Statement stmt = conn.createStatement();
        String sql = "SELECT name FROM t_category;";
        ResultSet rs = stmt.executeQuery(sql);
        // 获取结果集
        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }
    }
}
