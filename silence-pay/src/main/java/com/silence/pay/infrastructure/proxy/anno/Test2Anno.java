package com.silence.pay.infrastructure.proxy.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AllinPayAnno
 *
 * 支付日志打印注解
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/2/19
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test2Anno {

    /**
     * 调用说明
     */
    String description() default "";
}