package com.silence.api;


import java.io.Serializable;

/**
 * Category
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
public class PayDto implements Serializable {

    private String orderNo;
    private String transactionId;
    private String amount;
    private String endTime;
    private String payType;
    private String status;
    private PayAttachDto attach;

    public PayAttachDto getAttach() {
        return attach;
    }

    public void setAttach(PayAttachDto attach) {
        this.attach = attach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
