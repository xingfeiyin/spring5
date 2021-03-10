package com.spring5.java.jvm.notinitialization;

/**
 * @author yinxf
 * @Date 2021/3/10
 * @Description
 **/
public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORLD = "hello world";
}
