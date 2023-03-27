package com.silence.pay.infrastructure.wechat.DO;


import com.alibaba.fastjson.JSON;

/**
 * PayDO
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/21
 */
public class PayDO {

    private String orderNo;
    private String body;
    private String amount;
    private PayAttachDO attach;

    public PayAttachDO getAttach() {
        return attach;
    }

    public void setAttach(PayAttachDO attach) {
        this.attach = attach;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
