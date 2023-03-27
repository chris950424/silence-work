package com.silence.pay.infrastructure.proxy.handle;

import com.alibaba.fastjson.JSON;
import com.silence.pay.infrastructure.proxy.anno.CreditPurchasePayAnno;
import com.silence.pay.infrastructure.proxy.common.BaseMethodAdviceHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

@Component
public class CreditPurchasePayHandler extends BaseMethodAdviceHandler<Object> {




    /**
     * 记录方法出入参和调用时长
     */
    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();


        long costTime = System.currentTimeMillis() - startTime;

    }

    @Override
    protected String getMethodDesc(ProceedingJoinPoint point) {
        Method targetMethod = getTargetMethod(point);
        // 获得方法上的 AllinPayAnno
        CreditPurchasePayAnno ann = targetMethod.getAnnotation(CreditPurchasePayAnno.class);
        String description = ann.description();
        // 如果没有指定方法说明，那么使用默认的方法说明
        if ("".equals(description)) {
            description = super.getMethodDesc(point);
        }
        return description;
    }

}