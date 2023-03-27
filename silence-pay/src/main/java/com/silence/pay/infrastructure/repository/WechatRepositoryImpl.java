package com.silence.pay.infrastructure.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.silence.pay.domain.wechat.entity.Pay;
import com.silence.pay.domain.wechat.entity.PayAttach;
import com.silence.pay.domain.wechat.repository.WechatRepository;
import com.silence.pay.infrastructure.repository.converter.PayConverter;
import com.silence.pay.infrastructure.util.QRCodeUtil;
import com.silence.pay.infrastructure.wechat.DO.PayDO;
import com.silence.pay.infrastructure.wechat.WechatPayRequest;
import com.silence.pay.infrastructure.wechat.config.WechatPayConfig;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Map;

/**
 * WechatRepositoryImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/21
 */
@Repository
public class WechatRepositoryImpl implements WechatRepository {

    @Resource
    private WechatPayConfig wechatPayConfig;

    private static WechatPayRequest wechatPayRequest;

    private WechatPayRequest getPayRequest() {
        if (wechatPayRequest == null) {
            synchronized (this) {
                if (wechatPayRequest == null) {
                    wechatPayRequest = new WechatPayRequest(wechatPayConfig);
                }
            }
        }
        return wechatPayRequest;
    }


    @Override
    public String pay(Pay pay) {
        int amount = pay.getAmount().multiply(new BigDecimal(100)).intValue();
        PayDO payDO = PayConverter.INSTANCE.payToPayDo(pay);
        payDO.setAmount(String.valueOf(amount));
        Map<String, String> pay1 = getPayRequest().pay(payDO);
        byte[] bytes = QRCodeUtil.encodeByte(pay1.get("code_url"), "D:/code/silence/silence-pay/src/main/resources/qrBg.png", null, false);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public Pay query(String orderNo) {
        Map<String, String> result = getPayRequest().query(orderNo);
        return transferResultToPay(result);
    }

    @Override
    public Pay callBack(String sb) {
        Map<String, String> stringObjectMap = getPayRequest().callBack(sb);
        System.out.println(JSON.toJSONString(stringObjectMap));
        return transferResultToPay(stringObjectMap);
    }

    private Pay transferResultToPay(Map<String, String> result) {
        Pay pay = null;
        if (!CollectionUtils.isEmpty(result)) {
            pay = new Pay();
            pay.setTransactionId(result.get("transaction_id"));
            pay.setOrderNo(Long.parseLong(result.get("out_trade_no")));
            pay.setEndTime(result.get("time_end"));
            pay.setResult(result.get("result_code"));
            pay.setAttach(JSON.parseObject(result.get("attach"), PayAttach.class));
            pay.setStatus(2);
        }
        return pay;
    }
}
