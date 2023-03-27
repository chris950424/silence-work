package com.silence.admin.ui.entity.command;

/**
 * RoleVo
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
public class UpdateAttributeCommand {
    private String attributeId;
    private String categoryId;
    private String attributeType;
    private String attribute;
    private String attributeMoney;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
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
}
