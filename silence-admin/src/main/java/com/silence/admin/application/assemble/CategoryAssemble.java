package com.silence.admin.application.assemble;

import com.silence.admin.domain.authority.useradmin.entity.UserAdmin;
import com.silence.admin.ui.entity.command.*;
import com.silence.admin.ui.entity.dto.UserAdminDTO;
import com.silence.api.CategoryAttributeDto;
import com.silence.api.CategoryDto;
import com.silence.api.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * PermissionAssemble
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface CategoryAssemble {
    CategoryAssemble INSTANCE = Mappers.getMapper(CategoryAssemble.class);

    
    CategoryAttributeDto aacToAttributeDto(AddAttributeCommand aac);

    CategoryDto uccToCategoryDto(UpdateCategoryCommand ucc);

    CategoryDto accToCategoryDto(AddCategoryCommand acc);

    CategoryAttributeDto uacToAttributeDto(UpdateAttributeCommand uac);
}
