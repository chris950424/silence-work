package com.silence.product.ui.web;

import com.silence.api.ProductDto;
import com.silence.product.application.service.ProductApplicationService;
import com.silence.product.infrastructure.base.ApiResponse;
import com.silence.product.ui.entity.query.ProductListQuery;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductApplicationService applicationService;

    @PostMapping( value = "/productsByCategoryId")
    public ApiResponse<List<ProductDto>> productsByCategoryId(@RequestBody  ProductListQuery plq) {
        List<ProductDto> productDtos = applicationService.productsByCategoryId(plq);
        return ApiResponse.success(productDtos);
    }
}
