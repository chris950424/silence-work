package com.silence.category.ui.admin;

import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.category.application.service.CategoryApplicationService;
import com.silence.category.infrastructure.base.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CategoryController
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@RestController("AdminCategoryController")
@RequestMapping("/admin/category")
public class CategoryController {


    @Resource
    private CategoryApplicationService applicationService;

    @GetMapping(value = "/categoryList")
    public List<CategoryDto> categoryList() {
        return applicationService.categoryList();
    }


    @GetMapping(value = "/categoryById")
    public CategoryDto categoryById(Long id) {
        return applicationService.categoryById(id);
    }

    @GetMapping(value = "/addAttribute")
    public String addAttribute(CategoryAttributeDto attributeDTO) {
        return applicationService.addAttribute(attributeDTO);
    }

    @GetMapping(value = "/addCategory")
    public String addCategory(CategoryDto categoryDto) {
        return applicationService.addCategory(categoryDto);
    }

    @GetMapping(value = "/updateCategory")
    public String updateCategory(CategoryDto categoryDto) {
        return applicationService.updateCategory(categoryDto);
    }

    @GetMapping(value = "/updateAttribute")
    public String updateAttribute(CategoryAttributeDto attributeDTO) {
        return applicationService.updateAttribute(attributeDTO);
    }
}
