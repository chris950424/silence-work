package com.silence.api;


import java.io.Serializable;

/**
 *  CategoryAttributeDto
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/10
 */
public class CategoryAttributeDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  attributeId;
    private String categoryId;
    private String attributeType;
    private String attribute;
    private String attributeMoney;
    private String attributeStatus;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeStatus() {
        return attributeStatus;
    }

    public void setAttributeStatus(String attributeStatus) {
        this.attributeStatus = attributeStatus;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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

    public String getAttributeMoney() {
        return attributeMoney;
    }

    public void setAttributeMoney(String attributeMoney) {
        this.attributeMoney = attributeMoney;
    }

    @Override
    public String toString() {
        return "CategoryAttributeDto{" +
                "categoryId='" + categoryId + '\'' +
                ", attributeType='" + attributeType + '\'' +
                ", attribute='" + attribute + '\'' +
                ", attributeMoney='" + attributeMoney + '\'' +
                '}';
    }
}
