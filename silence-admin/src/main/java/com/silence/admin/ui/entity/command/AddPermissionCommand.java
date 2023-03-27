package com.silence.admin.ui.entity.command;

/**
 * AddPermissionCommand
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/7
 */
public class AddPermissionCommand {
    private String pid;
    private String code;
    private String type;
    private String description;
    private String url;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
