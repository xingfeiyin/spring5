package com.spring5.java.dynamicproxy.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
 **/
public class MyInvocationHandler implements InvocationHandler {

    Object target ;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入了invoke");
        method.invoke(target);
        System.out.println("执行了invoke");
        return null;
    }
}
