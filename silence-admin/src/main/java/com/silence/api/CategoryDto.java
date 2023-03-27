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
    private List<CategoryDto> children;
    private List<CategoryAttributeDto> attributes;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public List<CategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

    public List<CategoryAttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CategoryAttributeDto> attributes) {
        this.attributes = attributes;
    }
}
