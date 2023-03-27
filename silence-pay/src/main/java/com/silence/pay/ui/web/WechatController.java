package com.silence.pay.ui.web;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.silence.api.PayDto;
import com.silence.pay.application.service.PayApplicationService;
import com.silence.pay.domain.wechat.entity.WechatEnum;
import com.silence.pay.infrastructure.base.CallBackResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * WechatController
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
@RestController
@RequestMapping("/pay")
public class WechatController {

    @Resource
    private PayApplicationService service;

    @Resource
    RedissonClient redissonClient;

    /**
     * 微信支付回调
     *
     * @param request
     * @return
     */
    @PostMapping("/wechatPayCallback")
    public void wechatPayCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Stream<String> lines = request.getReader().lines();
        StringBuilder sb = new StringBuilder();
        lines.forEach(sb::append);
        String result = service.callback(WechatEnum.WECHAT_PAY.getCode(), "<xml><appid><![CDATA[wx0a27d3dc5c23946a]]></appid><attach><![CDATA[{\"userId\":\"3\"}]]></attach><bank_type><![CDATA[OTHERS]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1485402842]]></mch_id><nonce_str><![CDATA[5K8264ILTKCH16CQ2502SI8ZNMTM67VS]]></nonce_str><openid><![CDATA[opSXg0dJbFSPcahWPxP2Jw6SzO0U]]></openid><out_trade_no><![CDATA[6162070050082193408]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[5E41186D13643653BE23ECCC48E917DE]]></sign><time_end><![CDATA[20220124163828]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[NATIVE]]></trade_type><transaction_id><![CDATA[4200001376202201247281371405]]></transaction_id></xml>\n");
        CallBackResponse callBackResponse = new CallBackResponse();
        callBackResponse.setCode(result);
        response.getOutputStream().write(JSON.toJSONBytes(callBackResponse));
    }

    @GetMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RLock lock = redissonClient.getLock("lockName");
        lock.lock(10, TimeUnit.SECONDS);
        try {
            System.out.println("处理业务中");
            Thread.sleep(8000);
            //处理业务逻辑代码......
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        //lock.isLocked()误区：此方法不是判断当前线程是否被锁定，而是判断该锁对象当前是否被任意线程所持有。
        // 所以不能使用if(lock.isLocked()){lock.unlock();}的写法来释放锁。

        System.out.println("处理业务完毕");
        PayDto payDto = new PayDto();
        payDto.setPayType(WechatEnum.WECHAT_PAY.getCode());
        service.pay(payDto);
    }
}
