package com.spring5.java.jvm.notinitialization;

/**
 * @author yinxf
 * @Date 2021/3/10
 * @Description
 *      通过数据定义来引用类，不会触发此类的初始化
 **/
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10] ;
    }
}
