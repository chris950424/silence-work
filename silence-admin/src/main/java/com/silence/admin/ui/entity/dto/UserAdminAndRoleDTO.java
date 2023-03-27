package com.silence.admin.ui.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * UserVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class UserAdminAndRoleDTO {
    String userId;
    List<String> roleIds;
    List<UserAdminDTO> users;
    List<RoleDTO> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<UserAdminDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserAdminDTO> users) {
        this.users = users;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
