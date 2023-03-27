package com.silence.admin.domain.authority.role.service.impl;

import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.domain.authority.role.repository.RoleRepository;
import com.silence.admin.domain.authority.role.service.RoleService;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RoleService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository repository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updatePermissionForRole(Role role) {
        List<Long> oldPermissions = getRolePermissionsByRoleId(role.getId());
        List<Long> newPermissions = role.getPermissionIds();
        List<Long> changePermissions = oldPermissions.stream().filter(a -> !newPermissions.contains(a)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(changePermissions)) {
            role.setPermissionIds(changePermissions);
            deletePermissionForRole(role);
        }

        changePermissions = newPermissions.stream().filter(a -> !oldPermissions.contains(a)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(changePermissions)) {
            role.setPermissionIds(changePermissions);
            addPermissionForRole(role);
        }

        return "success";
    }

    @Override
    public String addPermissionForRole(Role role) {
        int i = repository.savePermission(role);
        return "success";
    }

    @Override
    public List<Long> getRolePermissionsByRoleId(Long id) {
        List<Long> permissions = repository.findPermissionIds(id);
        return permissions;
    }

    @Override
    public String updateRole(Role role) {
        int i = repository.save(role);
        return "success";
    }

    @Override
    public String addRole(Role role) {
        int i = repository.save(role);
        return "success";
    }

    @Override
    public String deleteRole(String id) {
        int i = repository.deleteRole(id);
        return "success";
    }

    @Override
    public String deletePermissionForRole(Role role) {
        int i = repository.removePermission(role);
        return "success";
    }

    @Override
    public List<Role> roleList() {
        return repository.findRole();
    }
}
