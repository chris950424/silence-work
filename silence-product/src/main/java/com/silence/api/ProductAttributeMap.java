package com.silence.api;


import java.io.Serializable;
import java.util.List;

public class ProductAttributeMap implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private List<ProductAttributeDto> attributeDtos;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ProductAttributeDto> getAttributeDtos() {
        return attributeDtos;
    }

    public void setAttributeDtos(List<ProductAttributeDto> attributeDtos) {
        this.attributeDtos = attributeDtos;
    }
}
