package com.silence.pay.infrastructure.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WechatPayConfig
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
@Component
@ConfigurationProperties(prefix = "wechat.pay")
public class WechatPayConfig {

    private String domain;

    private String appId;

    private String mrchId;

    private String notifyUrl;

    private String signType;

    private String appKey;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMrchId() {
        return mrchId;
    }

    public void setMrchId(String mrchId) {
        this.mrchId = mrchId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
