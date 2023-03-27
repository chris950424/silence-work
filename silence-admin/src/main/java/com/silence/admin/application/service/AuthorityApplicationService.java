package com.silence.admin.application.service;


import com.silence.admin.application.assemble.PermissionAssemble;
import com.silence.admin.application.assemble.RoleAssemble;
import com.silence.admin.application.assemble.UserAdminAssemble;
import com.silence.admin.domain.authority.client.service.ClientService;
import com.silence.admin.domain.authority.permission.entity.Permission;
import com.silence.admin.domain.authority.permission.service.PermissionService;
import com.silence.admin.domain.authority.role.entity.Role;
import com.silence.admin.domain.authority.role.service.RoleService;
import com.silence.admin.domain.authority.useradmin.entity.UserAdmin;
import com.silence.admin.domain.authority.useradmin.service.UserAdminService;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.api.UserDto;
import com.silence.admin.ui.entity.command.*;
import com.silence.admin.ui.entity.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * authorityApplicationServcie
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/29
 */
@Service
public class AuthorityApplicationService {
    Logger logger = LoggerFactory.getLogger(AuthorityApplicationService.class);

    @Resource
    UserAdminService userAdminService;
    @Resource
    RoleService roleService;
    @Resource
    PermissionService permissionService;
    @Resource
    ClientService clientService;

    public ApiResponse<String> deleteClient(String clientId) {
        return clientService.deleteClient(clientId);
    }

    public ApiResponse<String> addClient(ClientDTO dto) {
        return clientService.addClient(dto);
    }

    public ApiResponse<String> updatePermission(UpdatePermissionCommand upc) {
        Permission permission = PermissionAssemble.INSTANCE.updatePermissionCommandToPermission(upc);
        String s = permissionService.updatePermission(permission);
        return ApiResponse.success(s);
    }

    public ApiResponse<String> addPermission(AddPermissionCommand apc) {
        Permission permission = PermissionAssemble.INSTANCE.addPermissionCommandToPermission(apc);
        String s = permissionService.addPermission(permission);
        return ApiResponse.success(s);
    }

    public ApiResponse<String> deletePermission(String id) {
        String s = permissionService.deletePermission(Long.parseLong(id));
        return ApiResponse.success(s);
    }

    public ApiResponse<String> updatePermissionForRole(RoleAndPermissionCommand rapc) {
        Role role = new Role();
        role.setId(Long.parseLong(rapc.getId()));
        List<Permission> permissionList = permissionService.getPermissionList(rapc.getPermissionUrls());
        role.setPermissionIds(permissionList.stream().map(Permission::getId).collect(Collectors.toList()));
        return ApiResponse.success(roleService.updatePermissionForRole(role));
    }

    public ApiResponse<String> deletePermissionForRole(RoleAndPermissionDTO dto) {
        return ApiResponse.success(roleService.deletePermissionForRole(null));
    }

    public ApiResponse<String> addRole(AddRoleCommand arc) {
        return ApiResponse.success(roleService.addRole(RoleAssemble.INSTANCE.arcToRole(arc)));
    }

    public ApiResponse<String> updateRole(UpdateRoleCommand urc) {
        return ApiResponse.success(roleService.updateRole(RoleAssemble.INSTANCE.urcToRole(urc)));
    }

    public ApiResponse<String> deleteRole(String id) {
        return ApiResponse.success(roleService.deleteRole(id));
    }

    public ApiResponse<String> addUserAdmin(AddUserAdminCommand uac) {
        return ApiResponse.success(userAdminService.addUserAdmin(UserAdminAssemble.INSTANCE.auacToUserAdmin(uac)));

    }

    public ApiResponse<String> updateUserAdmin(UpdateUserAdminCommand uuac) {

        return ApiResponse.success(userAdminService.updateUserAdmin(UserAdminAssemble.INSTANCE.uuacToUserAdmin(uuac)));
    }

    public ApiResponse<String> deleteUserAdmin(String id) {
        return ApiResponse.success(userAdminService.deleteUserAdmin(Long.parseLong(id)));
    }


    public List<String> findPermissionsByUserAdminId(String id) {
        return userAdminService.findPermissionsByUserAdminId(Long.parseLong(id));
    }

    public UserDto getUserAdminByUserAdminName(String username) {
        UserAdmin userAdminByUserAdminName = userAdminService.getUserAdminByUserAdminName(username);
        return UserAdminAssemble.INSTANCE.userAdminToUserDto(userAdminByUserAdminName);
    }

    public ApiResponse<List<RoleDTO>> roleList() {
        List<Role> roles = roleService.roleList();
        List<RoleDTO> roleDTOS = RoleAssemble.INSTANCE.rolesToRoleDTOs(roles);
        return ApiResponse.success(roleDTOS);
    }

    public ApiResponse<List<PermissionDTO>> permissionList() {
        List<Permission> permissions = permissionService.permissionList();
        Map<Long, List<Permission>> permissionMap = permissions.stream().collect(Collectors.groupingBy(Permission::getPid));
        permissions = permissionMap.get(0L);
        if (!CollectionUtils.isEmpty(permissions)) {
            return ApiResponse.success(fullPermissions(permissions, permissionMap));
        }
        return ApiResponse.success();
    }

    public List<PermissionDTO> fullPermissions(List<Permission> permissions, Map<Long, List<Permission>> permissionMap) {
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        permissions.forEach(a -> {
            PermissionDTO permissionDTO = PermissionAssemble.INSTANCE.permissionToPermissionDto(a);
            List<Permission> childPermissions = permissionMap.get(a.getId());
            if (!CollectionUtils.isEmpty(childPermissions)) {
                permissionDTO.setChildren(fullPermissions(childPermissions, permissionMap));
            }
            permissionDTOS.add(permissionDTO);
        });
        return permissionDTOS;
    }

    public ApiResponse<UserAdminAndRoleDTO> userAdminList() {
        UserAdminAndRoleDTO adminAndRoleDTOS = new UserAdminAndRoleDTO();
        List<Role> roles = roleService.roleList();
        List<RoleDTO> roleDTOS = RoleAssemble.INSTANCE.rolesToRoleDTOs(roles);
        adminAndRoleDTOS.setRoles(roleDTOS);
        List<UserAdmin> userAdmins = userAdminService.userAdminList();
        List<UserAdminDTO> userAdminDTOS = UserAdminAssemble.INSTANCE.userAdminsToUserAdminDTOS(userAdmins);
        adminAndRoleDTOS.setUsers(userAdminDTOS);
        logger.info("adminAndRoleDTOS:{}", adminAndRoleDTOS);
        return ApiResponse.success(adminAndRoleDTOS);
    }

//    public ApiResponse<String> addRoleForUserAdmin(UserAdminAndRoleDTO vo) {
//        return userAdminService.addRoleForUserAdmin(vo);
//    }

    public ApiResponse<String> setRoleForUserAdmin(UserAdminAndRoleCommand uaarc) {
        return ApiResponse.success(userAdminService.setRoleForUserAdmin(UserAdminAssemble.INSTANCE.uaarcToUserAdmin(uaarc)));
    }
}
