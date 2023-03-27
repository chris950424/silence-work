package com.silence.admin.infrastructure.repository.converter;

import com.silence.admin.domain.authority.useradmin.entity.UserAdmin;
import com.silence.admin.infrastructure.repository.DO.UserAdminDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *  PermissionConverter
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
@Mapper
public interface UserAdminConverter {

    UserAdminConverter INSTANCE = Mappers.getMapper(UserAdminConverter.class);


    /**
     * 转换数据
     *
     * @param userAdmin
     * @return
     */
    UserAdminDO userAdminToUserAdminDO(UserAdmin userAdmin);
    
    UserAdmin userAdminDOToUserAdmin(UserAdminDO dO);

    List<UserAdmin> userAdminPosToUserAdmins(List<UserAdminDO> userAdminPos);
}
