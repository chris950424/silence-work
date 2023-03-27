package com.silence.pay.infrastructure.proxy.aspect;

import com.silence.pay.infrastructure.proxy.common.BaseMethodAspect;
import com.silence.pay.infrastructure.proxy.common.MethodAdviceHandler;
import com.silence.pay.infrastructure.proxy.handle.CreditPurchasePayHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  AllinPayAspect
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/2/19
 */
@Aspect
@Order(1)
@Component
public class CreditPurchasePayAspect extends BaseMethodAspect {

    /**
     * 指定切点（处理打上 InvokeRecordAnno 的方法）
     */
    @Override
    @Pointcut("@annotation(com.silence.pay.infrastructure.proxy.anno.CreditPurchasePayAnno)")
    protected void pointcut() { }

    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        return CreditPurchasePayHandler.class;
    }
}
