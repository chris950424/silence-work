package com.silence.pay.infrastructure.proxy.handle;

import com.silence.pay.infrastructure.proxy.anno.AllinPayAnno;
import com.silence.pay.infrastructure.proxy.common.BaseMethodAdviceHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AllinPayHandler
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/5/10
 */
@Component
public class AllinPayHandler extends BaseMethodAdviceHandler<Object> {


    @Override
    public boolean onBefore(ProceedingJoinPoint point) {
        System.out.println("onBefore");
        return true;
    }

    /**
     * 记录方法出入参和调用时长
     */
    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("onComplete");
    }

    @Override
    protected String getMethodDesc(ProceedingJoinPoint point) {
        Method targetMethod = getTargetMethod(point);
        // 获得方法上的 AllinPayAnno
        AllinPayAnno ann = targetMethod.getAnnotation(AllinPayAnno.class);
        String description = ann.description();
        // 如果没有指定方法说明，那么使用默认的方法说明
        if ("".equals(description)) {
            description = super.getMethodDesc(point);
        }
        return description;
    }
}