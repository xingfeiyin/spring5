package com.spring5.java.dynamicproxy.test2;

import com.spring5.java.dynamicproxy.test1.UserService;
import com.spring5.java.dynamicproxy.test1.UserServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.*;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description JDK动态代理
 *
 * 1、类加载器是干嘛的：JDK内部需要通过类加载作为缓存的key ； 需要类加载器生成class
 * 2、为什么需要接口： 因为生成的代理类需要实现这个接口
 * 3、为什么JDK动态代理只能代理接口： 因为生成的代理类已经继承了 Proxy类，java是单继承的，所以没法再继承一个类。
 *
 **/
public class Main {
    public static void main(String[] args) {
        MockInvocationHandler handler = new MockInvocationHandler(new UserServiceImpl());
        UserService userService = (UserService) MockProxy.newProxyInstance(UserService.class,handler);
        userService.query();

        test2();
    }

    private static void test2() {
        byte[] $proxy = ProxyGenerator.generateProxyClass("$Proxy",new Class[]{UserService.class});
        File file = new File("D://$Proxy.class");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            try {
                outputStream.write($proxy);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
