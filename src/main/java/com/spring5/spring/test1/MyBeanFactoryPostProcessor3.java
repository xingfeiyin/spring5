package com.spring5.spring.test1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author yinxf
 * @Date 2020/9/18
 * @Description
 **/
@Component
public class MyBeanFactoryPostProcessor3 implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        factory.getBeanDefinition("repo3").setScope("prototype");
    }
}
