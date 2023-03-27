package com.silence.admin.domain.authority.permission.service;

import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.PermissionDTO;

import java.util.*;

/**
 * PermissionService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public interface PermissionService {

    String addPermission(Permission permission);

     String updatePermission(Permission permission);

     String deletePermission(Long id) ;

     List<Permission> getPermissionList(List<String> urls) ;

     List<Permission> permissionList() ;
}
