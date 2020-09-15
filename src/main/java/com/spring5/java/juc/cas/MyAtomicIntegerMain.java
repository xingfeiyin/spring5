package com.spring5.java.juc.cas;

/**
 * @author yinxf
 * @Date 2020/9/7
 * @Description
 **/
public class MyAtomicIntegerMain {
    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        MyAtomicInteger atomicInteger = new MyAtomicInteger();
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.increment(1);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(atomicInteger.get());
    }
}
