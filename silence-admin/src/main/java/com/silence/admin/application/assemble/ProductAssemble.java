package com.silence.admin.application.assemble;

import com.silence.admin.ui.entity.command.*;
import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.api.ProductAttributeDto;
import com.silence.api.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * PermissionAssemble
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface ProductAssemble {
    ProductAssemble INSTANCE = Mappers.getMapper(ProductAssemble.class);

    ProductDto apcToProductDto(AddProductCommand apc);

    ProductAttributeDto aacToAttributeDto(AddAttributeCommand aac);
}
