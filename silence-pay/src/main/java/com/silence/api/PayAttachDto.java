package com.silence.api;


import java.io.Serializable;

/**
 * Category
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class PayAttachDto implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
