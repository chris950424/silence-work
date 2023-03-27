package com.silence.admin.infrastructure.repository.DO;

import com.silence.admin.infrastructure.interceptor.mybatis.CryptField;

import java.util.List;

/**
 * RolePo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class RoleDO {
    private Long id;
    @CryptField
    private String roleName;
    @CryptField
    private String description;
    @CryptField
    private String createTime;
    @CryptField
    private String updateTime;
    @CryptField
    private Integer status;
    private List<String> urls;
    private List<PermissionDO> permissionDos;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PermissionDO> getPermissionDos() {
        return permissionDos;
    }

    public void setPermissionDos(List<PermissionDO> permissionDos) {
        this.permissionDos = permissionDos;
    }
}
