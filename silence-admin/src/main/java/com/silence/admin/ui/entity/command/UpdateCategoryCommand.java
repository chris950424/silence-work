package com.silence.admin.ui.entity.command;

/**
 * RoleVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class UpdateCategoryCommand {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
