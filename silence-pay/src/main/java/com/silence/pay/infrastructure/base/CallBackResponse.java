package com.silence.pay.infrastructure.base;

import java.io.Serializable;

/**
 *  CallBackResponse
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
public class CallBackResponse implements Serializable {
    
    private String code;
    public String message;

    public CallBackResponse() {
        this.message = "成功";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
