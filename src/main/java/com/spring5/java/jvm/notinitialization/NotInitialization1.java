package com.spring5.java.jvm.notinitialization;

/**
 * @author yinxf
 * @Date 2021/3/10
 * @Description
 *      通过子类引用父类的静态字段，不会导致子类初始化
 **/
public class NotInitialization1 {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
