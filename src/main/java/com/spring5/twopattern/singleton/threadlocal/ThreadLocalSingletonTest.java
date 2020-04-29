package com.spring5.twopattern.singleton.threadlocal;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 * ThreadLocal如何实现这样的效果的？
 * 单例模式为了达到线程安全，会给方法加锁，以时间还空间。ThreadLocal将所有的对象都存放在TheadLocalMap中，为每个线程都提供一个对象，
 * 实际上是以空间换时间实现线程隔离。
 *
 **/
public class ThreadLocalSingletonTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("end");
    }
}
