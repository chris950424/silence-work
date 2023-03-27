package com.silence.admin.ui.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * PermissionVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class PermissionDTO {
    private String id;
    private String pid;
    private String code;
    private String type;
    private String description;
    private String url;
    private List<PermissionDTO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<PermissionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDTO> children) {
        this.children = children;
    }
}
