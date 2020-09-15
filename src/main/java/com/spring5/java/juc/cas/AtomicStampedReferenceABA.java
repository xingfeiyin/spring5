package com.spring5.java.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yinxf
 * @Date 2020/9/7
 * @Description ABA问题
 *
 **/
public class AtomicStampedReferenceABA {
    public static void main(String[] args) {
        try {
            AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(150,0);
            Thread thread1 = new Thread(()->{
                Integer oldValue = atomicStampedReference.getReference();
                int stamp = atomicStampedReference.getStamp();
                if (atomicStampedReference.compareAndSet(oldValue,50,0,stamp+1)){
                    System.out.println("150->50 成功：" + (stamp + 1));
                }
            });
            thread1.start();

            Thread thread2 = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer oldValue = atomicStampedReference.getReference();
                int stamp = atomicStampedReference.getStamp();
                if (atomicStampedReference.compareAndSet(oldValue,150,stamp,stamp+1)){
                    System.out.println("50->150 成功："+(stamp + 1));
                }
            });
            thread2.start();

            Thread thread3 = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer oldValue = atomicStampedReference.getReference();
                int stamp = atomicStampedReference.getStamp();
                if (atomicStampedReference.compareAndSet(oldValue,90,0,stamp+1)){
                    System.out.println("150->90成功："+(stamp+1));
                }
            });
            thread3.start();


            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println("现在的值是："+atomicStampedReference.getReference()+";stamp是："+atomicStampedReference.getStamp());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
