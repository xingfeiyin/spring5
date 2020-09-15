package com.spring5.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description
 **/
public class AtomicIntegerTest {
    public static void main(String[] args) {
        int num = 200 ;
        Thread[] threads = new Thread[num];
        AtomicInteger atomicInteger = new AtomicInteger();

        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.incrementAndGet();
                }
            });

            threads[i].start();
        }

        for (int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("x="+atomicInteger);

    }
}
