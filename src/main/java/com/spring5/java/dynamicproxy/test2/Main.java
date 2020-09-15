package com.spring5.java.dynamicproxy.test2;

import com.spring5.java.dynamicproxy.test1.UserService;
import com.spring5.java.dynamicproxy.test1.UserServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.*;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
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
