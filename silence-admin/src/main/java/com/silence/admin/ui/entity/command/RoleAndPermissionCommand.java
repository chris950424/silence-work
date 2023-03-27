package com.silence.admin.ui.entity.command;

import lombok.Data;

import java.util.List;

/**
 * RoleVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class RoleAndPermissionCommand {
    private String id;
    private List<String> permissionUrls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPermissionUrls() {
        return permissionUrls;
    }

    public void setPermissionUrls(List<String> permissionUrls) {
        this.permissionUrls = permissionUrls;
    }
}
