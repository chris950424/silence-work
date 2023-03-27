package com.silence.pay.infrastructure.proxy.handle;

import com.silence.pay.infrastructure.proxy.common.BaseMethodAdviceHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CreditPurchaseExceptionHandleHandler extends BaseMethodAdviceHandler<Object> {



    /**
     * 抛出异常时的处理
     */
    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        super.onThrow(point, e);
        // 发送异常到邮箱或者钉钉的逻辑
    }

    /**
     * 抛出异常时的返回值
     */
    @Override
    public Object getOnThrow(ProceedingJoinPoint point, Throwable e) {
        Class<?> returnType = getTargetMethod(point).getReturnType();
        // 如果返回值类型是 Map 或者其子类
        return null;
    }
}