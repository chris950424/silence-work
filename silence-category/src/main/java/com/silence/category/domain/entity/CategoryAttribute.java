package com.silence.category.domain.entity;


import java.math.BigDecimal;

/**
 * Category
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class CategoryAttribute {

    private Long attributeId;
    private Long categoryId;
    private String attributeType;
    private String attribute;
    private BigDecimal attributeMoney;
    private Integer attributeStatus;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getAttributeStatus() {
        return attributeStatus;
    }

    public void setAttributeStatus(Integer attributeStatus) {
        this.attributeStatus = attributeStatus;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public BigDecimal getAttributeMoney() {
        return attributeMoney;
    }

    public void setAttributeMoney(BigDecimal attributeMoney) {
        this.attributeMoney = attributeMoney;
    }

}
