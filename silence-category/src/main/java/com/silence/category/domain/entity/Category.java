package com.silence.category.domain.entity;


import java.util.Date;
import java.util.List;

/**
 * Category
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class Category {
    private Long id;
    private String name;
    private Long pid;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private List<CategoryAttribute> attributes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CategoryAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CategoryAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", attributes=" + attributes +
                '}';
    }
}
