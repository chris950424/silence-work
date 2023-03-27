package com.silence.admin.application.assemble;

import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.ui.entity.command.AddPermissionCommand;
import com.silence.admin.ui.entity.command.UpdatePermissionCommand;
import com.silence.admin.ui.entity.dto.PermissionDTO;
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
public interface PermissionAssemble {
    PermissionAssemble INSTANCE = Mappers.getMapper(PermissionAssemble.class);


    PermissionDTO permissionToPermissionDto(Permission a);

    Permission addPermissionCommandToPermission(AddPermissionCommand apc);

    Permission updatePermissionCommandToPermission(UpdatePermissionCommand upc);
}
