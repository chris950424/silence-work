package com.silence.admin.infrastructure.feign;

import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name = "silence-category")
@RequestMapping("/admin/category")
public interface CategoryApi {


    @GetMapping("/categoryList")
    List<CategoryDto> categoryList();

    @PostMapping("/categoryById")
    CategoryDto categoryById(Long id);

    @PostMapping("/addAttribute")
    String addAttribute(CategoryAttributeDto attributeDTO);

    @PostMapping("/addCategory")
    String addCategory(CategoryDto categoryDto);

    @PostMapping("/updateCategory")
    String  updateCategory(CategoryDto categoryDto);

    @PostMapping("/updateAttribute")
    String updateAttribute(CategoryAttributeDto attributeDTO);

}
