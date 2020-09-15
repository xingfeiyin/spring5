package com.spring5.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description
 **/
public class AtomicIntegerTest2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        new Thread(()->{
            if (!atomicInteger.compareAndSet(0,100)){
                System.out.println("0-100：失败");
            }
        }).start();

        new Thread(()->{
            try { //注意这里睡了一会儿，目的是让第三个线程先执行判断的操作，从而让第三个线程修改失败
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!atomicInteger.compareAndSet(100,50)){
                System.out.println("100-50：失败");
            }
        }).start();

        new Thread(()->{
            if (!atomicInteger.compareAndSet(50,60)){
                System.out.println("50-60：失败");
            }
        }).start();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
