package com.spring5.java.jvm;

/**
 * @author yinxf
 * @Date 2020/11/26
 * @Description
 **/
public class GCTest {
    public static void main(String[] args) {
        new GCTest();
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("GCTest重写了finalize方法");
    }
}
