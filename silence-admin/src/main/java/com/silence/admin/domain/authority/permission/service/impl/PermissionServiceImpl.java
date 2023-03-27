package com.silence.admin.domain.authority.permission.service.impl;

import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.domain.authority.permission.repository.PermissionRepository;
import com.silence.admin.domain.authority.permission.service.PermissionService;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.admin.ui.entity.dto.PermissionDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PermissionService
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@Service
public class PermissionServiceImpl implements PermissionService {


    @Resource
    private PermissionRepository repository;

    @Override
    public String addPermission(Permission permission) {
        int i = repository.save(permission);
        return String.valueOf(i);
    }

    @Override
    public String updatePermission(Permission permission) {
        int i = repository.save(permission);
        return String.valueOf(i);
    }

    @Override
    public String deletePermission(Long id) {
        int i = repository.remove(id);
        return String.valueOf(i);
    }

    @Override
    public List<Permission> getPermissionList(List<String> urls) {
        List<Permission> permissionList = repository.find();
        List<Permission> selectPermissions = permissionList.stream().filter(a -> urls.contains(a.getUrl())).collect(Collectors.toList());
        List<Permission> selectedPermissions = new ArrayList<>();
        selectPermissions.forEach(a -> {
            selectedPermissions.add(new Permission((a.getId())));
            getAllPermissions(a.getPid(), selectedPermissions, permissionList);
        });
        return new ArrayList<>(selectedPermissions);
    }

    public void getAllPermissions(Long pid, List<Permission> selectedPermissions, List<Permission> permissionList) {
        List<Permission> permissions = permissionList.stream().filter(a -> a.getId().equals(pid)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.forEach(a -> {
                selectedPermissions.add(new Permission((a.getId())));
                getAllPermissions(a.getPid(), selectedPermissions, permissionList);
            });
        }
    }

    @Override
    public List<Permission> permissionList() {
        return  repository.find();
    }

}


