package com.silence.admin.ui.entity.command;

/**
 * RoleVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class UpdateRoleCommand {
    private String id;
    private String roleName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
