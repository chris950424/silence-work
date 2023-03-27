package com.silence.admin.infrastructure.repository.converter;

import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.infrastructure.repository.DO.PermissionDO;
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
public interface PermissionConverter {

    PermissionConverter INSTANCE = Mappers.getMapper(PermissionConverter.class);


    /**
     * 转换数据
     *
     * @param permission
     * @return
     */
//    @Mapping(source = "numberOfSeats", target = "seatCount")
    PermissionDO permissionToPermissionDO(Permission permission);

    Permission permissionDOToPermission(PermissionDO permissionDO);

}
