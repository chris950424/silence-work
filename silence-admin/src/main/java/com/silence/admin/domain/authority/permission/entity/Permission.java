package com.silence.admin.domain.authority.permission.entity;

/**
 * PermissionPo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class Permission {
    private Long id;
    private Long pid;
    private String code;
    private String type;
    private String description;
    private String url;


    public Permission() {
    }

    public Permission(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
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
