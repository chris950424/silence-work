package com.silence.order.infrastructure.job.annotation;

import java.lang.annotation.*;

/**
 * @author Ricky Fung
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableElasticJob {

}
