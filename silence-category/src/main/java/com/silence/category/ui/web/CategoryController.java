package com.silence.category.ui.web;

import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.category.application.service.CategoryApplicationService;
import com.silence.category.infrastructure.base.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RestController("WebCategoryController")
@RequestMapping("/category")
public class CategoryController {


    @Resource
    private CategoryApplicationService applicationService;

    @GetMapping(value = "/categoryList")
    public ApiResponse<List<CategoryDto>> categoryList() {
        try {
            List<CategoryDto> categoryDtoList = applicationService.categoryList();
            Map<String, List<CategoryDto>> listMap = categoryDtoList.stream().collect(Collectors.groupingBy(CategoryDto::getPid));
            List<CategoryDto> list = listMap.get("0");
            list.forEach(a -> a.setChildren(listMap.get(a.getId())));
            return ApiResponse.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.fail();
    }
}
