package com.silence.pay.domain.wechat.repository;

import com.silence.pay.domain.wechat.entity.Pay;

/**
 *  WechatRepository
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/21
 */
public interface WechatRepository {
    String pay(Pay pay);

    Pay query(String orderNo);

    Pay callBack(String sb);
}
