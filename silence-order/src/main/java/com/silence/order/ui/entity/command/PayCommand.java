package com.silence.order.ui.entity.command;

import java.util.List;

/**
 * @author Administrator
 */
public class PayCommand {

    private  String orderNo;

    private  String payType;



    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
