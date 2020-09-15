package com.spring5.java.juc;

/**
 * @author yinxf
 * @Date 2020/8/24
 * @Description
 **/
public class Main {

    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();
        local.set("hello");
        System.out.println("当前线程是："+Thread.currentThread().getName()+";在当前线程中获取："+local.get());
        new Thread(()-> System.out.println("现在的线程是："+Thread.currentThread().getName()+";尝试获取："+local.get())).start();
    }
}
