package com.spring5.spring.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/9/17
 * @Description
 **/

public class MyInvocationHandler2 implements InvocationHandler {

    private Object target;

    public MyInvocationHandler2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进来了");
        Object object = method.invoke(target,args);
        System.out.println("出去了");
        return object;
    }
}
