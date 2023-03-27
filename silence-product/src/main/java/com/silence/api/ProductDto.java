package com.silence.api;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String categoryId;
    private Date createTime;
    private Date updateTime;
    private List<ProductAttributeDto> attributes;
    private List<ProductAttributeMap> attributeMap;
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

    public String getCategoryId() {
        return categoryId;
    }

    public List<ProductAttributeDto> getAttributes() {
        return attributes;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setAttributes(List<ProductAttributeDto> attributes) {
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

    public List<ProductAttributeMap> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(List<ProductAttributeMap> attributeMap) {
        this.attributeMap = attributeMap;
    }
}
