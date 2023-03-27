package com.silence.pay.infrastructure.proxy.aspect;

import com.silence.pay.infrastructure.proxy.common.BaseMethodAspect;
import com.silence.pay.infrastructure.proxy.common.MethodAdviceHandler;
import com.silence.pay.infrastructure.proxy.handle.CreditPurchaseExceptionHandleHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ExceptionHandleAspect
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/2/19
 */
@Aspect
@Order(10)
@Component
public class CreditPurchaseExceptionHandleAspect extends BaseMethodAspect {

    /**
     * 指定切点（处理打上 ExceptionHandleAnno 的方法）
     */
    @Override
    @Pointcut("@annotation(com.silence.pay.infrastructure.proxy.anno.CreditPurchaseExceptionHandleAnno)")
    protected void pointcut() {
    }

    /**
     * 指定该切面绑定的方法切面处理器为 ExceptionHandleHandler
     */
    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        return CreditPurchaseExceptionHandleHandler.class;
    }
}