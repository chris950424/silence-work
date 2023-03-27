package com.silence.admin.ui.entity.command;

import lombok.Data;

import java.util.List;

/**
 * UserVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class UserAdminAndRoleCommand {
    private String userId;
    private List<Long> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
