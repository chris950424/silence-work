package com.silence.pay.domain.wechat.entity;

/**
 * WechatEnum
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
public enum WechatEnum {
    WECHAT_PAY("WECHAT", "微信支付");
    private String code;
    private String description;

    WechatEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
