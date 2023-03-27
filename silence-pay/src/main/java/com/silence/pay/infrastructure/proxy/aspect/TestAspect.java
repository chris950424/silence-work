package com.silence.pay.infrastructure.proxy.aspect;



import com.silence.pay.infrastructure.proxy.anno.TestAnno;
import com.silence.pay.infrastructure.proxy.common.BaseMethodAspect;
import com.silence.pay.infrastructure.proxy.common.MethodAdviceHandler;
import com.silence.pay.infrastructure.proxy.handle.AllinPayHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *  AllinPayAspect
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/2/19
 */
@Aspect
@Order(2)
//@Component
public class TestAspect  {

    /**
     * 指定切点（处理打上 InvokeRecordAnno 的方法）
     */
    @Pointcut("@annotation(com.silence.pay.infrastructure.proxy.anno.TestAnno)")
    protected void pointcut() { }

    @Before("pointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        TestAnno annotation = method.getAnnotation(TestAnno.class);
        String value = annotation.description();
        System.out.println("准备"+value);
    }

    @After("pointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        TestAnno annotation = method.getAnnotation(TestAnno.class);
        String value = annotation.description();
        System.out.println("结束"+value);
    }
}
