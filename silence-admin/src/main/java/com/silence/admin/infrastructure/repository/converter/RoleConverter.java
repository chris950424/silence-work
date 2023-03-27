package com.silence.admin.infrastructure.repository.converter;

import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.infrastructure.repository.DO.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  PermissionConverter
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);


    /**
     * 转换数据
     *
     * @param roleDO
     * @return
     */
    Role roleDOToRole(RoleDO roleDO);

    RoleDO roleToRoleDo(Role role);
}
