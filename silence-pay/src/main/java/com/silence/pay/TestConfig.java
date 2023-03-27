package com.silence.pay;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class TestConfig implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, BeanPostProcessor {
    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("TestConfig====setBeanFactory");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestConfig====setApplicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestConfig====afterPropertiesSet");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("TestConfig====postProcessBeforeInitialization++++++++++++++" + beanName + "");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("TestConfig====postProcessAfterInitialization++++++++++++++" + beanName + "");
        return bean;
    }
}
