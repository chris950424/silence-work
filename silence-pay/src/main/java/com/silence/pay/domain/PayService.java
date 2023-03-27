package com.silence.pay.domain;

import com.silence.pay.domain.wechat.entity.Pay;

import java.util.Map;

/**
 * PayService
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
public interface PayService {

    /**
     * PayService
     *
     * @author leo
     * @version 1.1.0
     * @date 2022/1/20
     */
    String getPayType();

    /**
     * PayService
     *
     * @author leo
     * @version 1.1.0
     * @date 2022/1/20
     */
    String pay(Pay pay);

    /**
     * 支付回调
     * @return
     */
    Pay  callBack(String sb);

    Pay query(String orderNo);
}
