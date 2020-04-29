package com.spring5.twopattern.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 **/
public class GPMeiPo implements GPInvocationHandler {

    //被代理的对象，把引用保存下来
    private Object target;

    public Object getInstance(Object target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        before();
        method.invoke(this.target,args);
        after();
        return null;
    }

    private void after() {
        System.out.println("如果合适就办事。");
    }

    private void before() {
        System.out.println("我是媒婆，我要给你找对象。");
        System.out.println("开始物色");

    }
}
