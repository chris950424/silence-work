package com.silence.order.domain.entity;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 */
public class Pay {

    private Long orderNo;
    private String body;
    private BigDecimal amount;
    private String transactionId;
    private Date endTime;
    private Integer status;
    private PayAttach attach;

    public PayAttach getAttach() {
        return attach;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setAttach(PayAttach attach) {
        this.attach = attach;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if (!StringUtils.isEmpty(endTime)){
            Date parse = null;
            try {
                parse = new SimpleDateFormat("yyyyMMddHHmmss").parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.endTime = parse;
        }
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
