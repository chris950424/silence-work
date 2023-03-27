package com.silence.product.infrastructure.repository.DO;


import java.math.BigDecimal;

/**
 * CategoryDO
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class ProductAttributeDO {

    private Long id;
    private Long productId;
    private String type;
    private String attribute;
    private BigDecimal money;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
