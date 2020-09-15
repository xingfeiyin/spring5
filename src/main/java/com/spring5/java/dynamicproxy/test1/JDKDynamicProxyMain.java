package com.spring5.java.dynamicproxy.test1;

import java.lang.reflect.Proxy;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
 **/
public class JDKDynamicProxyMain {
    public static void main(String[] args) {
        MyInvocationHandler handler = new MyInvocationHandler(new UserServiceImpl());
        Object o = Proxy.newProxyInstance(JDKDynamicProxyMain.class.getClassLoader(),new Class[]{UserService.class},handler);
        ((UserService)o).query();
    }
}
