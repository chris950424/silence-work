package com.silence.order.ui.entity.command;

import java.util.List;

/**
 * @author Administrator
 */
public class CreateOrderCommand  {


    private String productId;

    private List<String> attributes;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
