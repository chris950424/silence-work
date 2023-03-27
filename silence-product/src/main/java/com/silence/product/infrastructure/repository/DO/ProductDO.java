package com.silence.product.infrastructure.repository.DO;


import java.util.Date;
import java.util.List;

/**
 * CategoryDO
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class ProductDO {
    private Long id;
    private String name;
    private Long categoryId;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private List<ProductAttributeDO> attributes;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductAttributeDO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttributeDO> attributes) {
        this.attributes = attributes;

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


}
