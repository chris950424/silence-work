package com.silence.order.domain.entity;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Category
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class Order {
    private Long id;
    private Long orderNo;
    private Long userId;
    private Long productId;
    private String transactionId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private Date endTime;
    private Integer status;
    private List<BigDecimal> attributes;


    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<BigDecimal> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<BigDecimal> attributes) {
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
