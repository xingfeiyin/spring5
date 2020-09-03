package com.spring5.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description 模拟生产者/消费者模型
 *    当有数据的时候，生产者停止生产数据，通知消费者消费数据
 *    当没有数据的时候，消费者停止消费数据，通知生产者生成数据
 *
 **/
public class ProducerConsumer {

    private boolean isHaveData = false;

    Lock lock = new ReentrantLock();

    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

    public void product(){
        lock.lock();

        try {
            while (isHaveData){
                try {
                    System.out.println("还有数据，等待消费数据！");
                    producer_con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产者开始生产数据！");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isHaveData = true ;
            consumer_con.signal();

        }finally {
            lock.unlock();
        }
    }



    public void consume(){
        lock.lock();
        try {
            while (!isHaveData){
                try {
                    System.out.println("没有数据了，等待生产者生成数据！");
                    consumer_con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者开始消费数据！");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isHaveData = false;
            producer_con.signal();
        }finally {
            lock.unlock();
        }
    }

}
