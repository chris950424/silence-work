package com.silence.admin.ui.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.silence.admin.application.service.CategoryApplicationService;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.command.*;
import com.silence.api.CategoryDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryController
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    private CategoryApplicationService applicationService;


//    @PreAuthorize("hasAuthority('categoryList')")
    @SentinelResource(value = "categoryList", blockHandler = "CustomUrlBlockHandler")
    @GetMapping(value = "/categoryList")
    public ApiResponse<List<CategoryDto>> categoryList() {
        return applicationService.categoryList();
    }


    @PreAuthorize("hasAuthority('categoryById')")
    @PostMapping("/categoryById")
    public ApiResponse<CategoryDto> categoryById(@RequestBody CategoryQuery cq) {
        return applicationService.categoryById(cq.getId());
    }

    @PreAuthorize("hasAuthority('attributeList')")
    @GetMapping("/attribute/attributeList")
    public ApiResponse<List<CategoryDto>> attributeList() {
        return applicationService.attributeList();
    }

    @PreAuthorize("hasAuthority('addCategory')")
    @PostMapping("/addCategory")
    public ApiResponse<String> addCategory(@RequestBody() AddCategoryCommand acc) {
        return applicationService.addCategory(acc);
    }

    @PreAuthorize("hasAuthority('updateCategory')")
    @PostMapping("/updateCategory")
    public ApiResponse<String> updateCategory(@RequestBody() UpdateCategoryCommand ucc) {
        return applicationService.updateCategory(ucc);
    }

}
