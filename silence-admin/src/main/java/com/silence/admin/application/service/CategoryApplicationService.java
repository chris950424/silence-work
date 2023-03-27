package com.silence.admin.application.service;

import com.silence.admin.application.assemble.CategoryAssemble;
import com.silence.admin.application.assemble.ProductAssemble;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.infrastructure.facade.CategoryFacade;
import com.silence.admin.ui.entity.command.*;
import com.silence.api.CategoryDto;
import com.silence.api.ProductDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * authorityApplicationServcie
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/29
 */
@Service
public class CategoryApplicationService {
    Log log = LogFactory.getLog(CategoryApplicationService.class);
    @Resource
    CategoryFacade categoryFacade;


    public ApiResponse<List<CategoryDto>> categoryList() {
        log.info("1111111111111111");
        List<CategoryDto> categoryDtos = categoryFacade.categoryList();
        Map<String, List<CategoryDto>> listMap = categoryDtos.stream().collect(Collectors.groupingBy(CategoryDto::getPid));
        categoryDtos = listMap.get("0");
        List<CategoryDto> categoryDtoList = fullCategory(categoryDtos, listMap);
        return ApiResponse.success(categoryDtoList);
    }

    private List<CategoryDto> fullCategory(List<CategoryDto> categoryDtos, Map<String, List<CategoryDto>> listMap) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryDtos)) {
            categoryDtos.forEach(a -> {
                        List<CategoryDto> children = listMap.get(a.getId());
                        if (!CollectionUtils.isEmpty(children)) {
                            a.setChildren(fullCategory(children, listMap));
                        }
                        categoryDtoList.add(a);
                    }
            );
        }
        return categoryDtoList;
    }


    public ApiResponse<CategoryDto> categoryById(Long id) {
        return ApiResponse.success(categoryFacade.categoryById(id));
    }


    public ApiResponse<String> addCategory(AddCategoryCommand acc) {
        CategoryDto categoryDto = CategoryAssemble.INSTANCE.accToCategoryDto(acc);
        return ApiResponse.success(categoryFacade.addCategory(categoryDto));
    }

    public ApiResponse<String> updateCategory(UpdateCategoryCommand ucc) {
        CategoryDto categoryDto = CategoryAssemble.INSTANCE.uccToCategoryDto(ucc);
        return ApiResponse.success(categoryFacade.updateCategory(categoryDto));
    }

    public ApiResponse<List<CategoryDto>> attributeList() {
        List<CategoryDto> categoryDtos = categoryFacade.categoryList();
        Map<String, List<CategoryDto>> listMap = categoryDtos.stream().collect(Collectors.groupingBy(CategoryDto::getPid));
        List<CategoryDto> categoryDtoList = listMap.get("0");
        List<CategoryDto> attributes = new ArrayList<>();
        fullAttribute(attributes, categoryDtoList, listMap);
        return ApiResponse.success(attributes);
    }

    private void fullAttribute(List<CategoryDto> attributes, List<CategoryDto> categoryDtos, Map<String, List<CategoryDto>> listMap) {
        if (!CollectionUtils.isEmpty(categoryDtos)) {
            categoryDtos.forEach(a -> {
                        List<CategoryDto> children = listMap.get(a.getId());
                        if (CollectionUtils.isEmpty(children)) {
                            attributes.add(a);
                        } else {
                            fullAttribute(attributes, children, listMap);
                        }

                    }
            );
        }
    }

}
