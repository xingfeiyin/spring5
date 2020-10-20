package com.spring5.spring.test1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @author yinxf
 * @Date 2020/9/17
 * @Description
 **/
@Component
public class MyBeanPostProcess2 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object o = Proxy.newProxyInstance(MyBeanPostProcess2.class.getClassLoader(),
                bean.getClass().getInterfaces(),new MyInvocationHandler2(bean));

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
