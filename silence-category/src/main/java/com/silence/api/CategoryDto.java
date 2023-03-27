package com.silence.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String pid;
    private Date createTime;
    private Date updateTime;
    private String status;
    private List<CategoryAttributeDto> attributes;
    private List<CategoryDto> children;

    public List<CategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<CategoryAttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CategoryAttributeDto> attributes) {
        this.attributes = attributes;
    }
}
