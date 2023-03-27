package com.silence.admin.ui.web;

import com.silence.admin.application.service.AuthorityApplicationService;
import com.silence.admin.infrastructure.base.ApiResponse;
import com.silence.api.UserDto;
import com.silence.admin.ui.entity.command.*;
import com.silence.admin.ui.entity.dto.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClientController
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@RestController()
@RequestMapping("/authority")
public class AuthorityController {


    @Resource
    AuthorityApplicationService applicationService;

    @PostMapping("/client/addClient")
    public ApiResponse<String> addClient(@RequestBody ClientDTO vo) {
        return applicationService.addClient(vo);
    }


    @GetMapping("/client/deleteClient/{clientId}")
    public ApiResponse<String> deleteClient(@PathVariable("clientId") String clientId) {
        return applicationService.deleteClient(clientId);
    }


    @PreAuthorize("hasAuthority('addPermission')")
    @PostMapping("/permission/addPermission")
    public ApiResponse<String> addPermission(@RequestBody AddPermissionCommand apc) {
        return applicationService.addPermission(apc);
    }

    @PreAuthorize("hasAuthority('permissionList')")
    @GetMapping("/permission/permissionList")
    public ApiResponse<List<PermissionDTO>> permissionList() {
        return applicationService.permissionList();
    }


    @PreAuthorize("hasAuthority('updatePermission')")
    @PostMapping("/permission/updatePermission")
    public ApiResponse<String> updatePermission(@RequestBody UpdatePermissionCommand upc) {
        return applicationService.updatePermission(upc);
    }

    @PreAuthorize("hasAuthority('deletePermission')")
    @GetMapping("/permission/deletePermission")
    public ApiResponse<String> deletePermission(String id) {
        return applicationService.deletePermission(id);
    }


    @PreAuthorize("hasAuthority('updatePermissionForRole')")
    @PostMapping("/role/updatePermissionForRole")
    public ApiResponse<String> updatePermissionForRole(@RequestBody RoleAndPermissionCommand rapc) {
        return applicationService.updatePermissionForRole(rapc);
    }

    @PreAuthorize("hasAuthority('roleList')")
    @GetMapping("/role/roleList")
    public ApiResponse<List<RoleDTO>> roleList() {
        return applicationService.roleList();
    }

    @PreAuthorize("hasAuthority('addRole')")
    @PostMapping("/role/addRole")
    public ApiResponse<String> addRole(@RequestBody AddRoleCommand arc) {
        return applicationService.addRole(arc);
    }

    @PreAuthorize("hasAuthority('updateRole')")
    @PostMapping("/role/updateRole")
    public ApiResponse<String> updateRole(@RequestBody UpdateRoleCommand urc) {
        return applicationService.updateRole(urc);
    }

    @PreAuthorize("hasAuthority('deleteRole')")
    @DeleteMapping("/role/deleteRole/{id}")
    public ApiResponse<String> deleteRole(@PathVariable("id") String id) {
        return applicationService.deleteRole(id);
    }


    @PreAuthorize("hasAuthority('addUserAdmin')")
    @PostMapping("/userAdmin/addUserAdmin")
    public ApiResponse<String> addUserAdmin(@RequestBody AddUserAdminCommand auac) {
        return applicationService.addUserAdmin(auac);
    }

    @PreAuthorize("hasAuthority('userAdminList')")
    @GetMapping("/userAdmin/userAdminList")
    public ApiResponse<UserAdminAndRoleDTO> userAdminList() {
        return applicationService.userAdminList();
    }

    @PreAuthorize("hasAuthority('updateUserAdmin')")
    @PostMapping("/userAdmin/updateUserAdmin")
    public ApiResponse<String> updateUserAdmin(@RequestBody UpdateUserAdminCommand uuac) {
        return applicationService.updateUserAdmin(uuac);
    }

    @PreAuthorize("hasAuthority('deleteUserAdmin')")
    @DeleteMapping("/userAdmin/deleteUserAdmin/{id}")
    public ApiResponse<String> deleteUserAdmin(@PathVariable("id") String id) {
        return applicationService.deleteUserAdmin(id);
    }

    @PreAuthorize("hasAuthority('setRoleForUserAdmin')")
    @PostMapping("/userAdmin/setRoleForUserAdmin")
    public ApiResponse<String> setRoleForUserAdmin(@RequestBody UserAdminAndRoleCommand uaarc) {
        return applicationService.setRoleForUserAdmin(uaarc);
    }


    @PreAuthorize("hasAuthority('getUserAdminInfo')")
    @GetMapping("/userAdmin/getUserAdminInfo")
    public ApiResponse<UserDto> getUserAdminInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = ((UserDto) authentication.getPrincipal());
        List<String> authorities = new ArrayList<>();
        authentication.getAuthorities().forEach(a -> {
            authorities.add(a.getAuthority());
        });
        userDto.setAuthorities(authorities);
        return ApiResponse.success(userDto);
    }
}
