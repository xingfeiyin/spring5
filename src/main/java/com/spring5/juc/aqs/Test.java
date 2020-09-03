package com.spring5.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description
 **/
public class Test {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add(){
        while (num < 100){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+":"+num++);
                condition.signal();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
