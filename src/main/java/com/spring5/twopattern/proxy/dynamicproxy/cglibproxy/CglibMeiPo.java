package com.spring5.twopattern.proxy.dynamicproxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 **/
public class CglibMeiPo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception{
        Enhancer enhancer = new Enhancer();
        //要把那个设置为即将生成的新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //业务增强
        before();
        Object obj = methodProxy.invokeSuper(o,objects);
        after();

        return obj;
    }

    private void after() {
        System.out.println("合适就办事");
    }

    private void before() {
        System.out.println("我是媒婆：给你找对象");
        System.out.println("开始物色");
    }
}
