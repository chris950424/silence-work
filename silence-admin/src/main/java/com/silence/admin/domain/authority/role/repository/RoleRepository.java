package com.silence.admin.domain.authority.role.repository;

import com.silence.admin.domain.authority.role.entity.Role;


import java.util.List;

/**
 * RoleService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public interface RoleRepository {


    int savePermission(Role role);


    int save(Role DO);

    int deleteRole(String id);

    int removePermission(Role role);

    List<Role> findRole();

    List<Long> findPermissionIds(Long id);
}
