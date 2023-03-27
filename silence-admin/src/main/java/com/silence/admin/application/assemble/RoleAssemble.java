package com.silence.admin.application.assemble;

import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.ui.entity.command.AddRoleCommand;
import com.silence.admin.ui.entity.command.UpdateRoleCommand;
import com.silence.admin.ui.entity.dto.RoleDTO;
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
public interface RoleAssemble {
    RoleAssemble INSTANCE = Mappers.getMapper(RoleAssemble.class);


    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    Role urcToRole(UpdateRoleCommand urc);

    Role arcToRole(AddRoleCommand arc);
}
