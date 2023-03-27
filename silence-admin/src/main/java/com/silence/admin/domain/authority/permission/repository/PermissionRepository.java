package com.silence.admin.domain.authority.permission.repository;

import com.silence.admin.domain.authority.permission.entity.Permission;


import java.util.List;

/**
 * PermissionService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public interface PermissionRepository {


    int save(Permission vo);

    int remove(long id);

    List<Permission> find();
}
