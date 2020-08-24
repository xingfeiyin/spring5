package com.spring5.juc.threadlocal;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinxf
 * @Date 2020/8/24
 * @Description
 **/
public class ThreadLocalTest {
    public static void main(String[] args) {
        CodeBearThreadLocal local = new CodeBearThreadLocal();
        local.set("test");
        System.out.println("当前线程是："+Thread.currentThread().getName() +";在当前线程中获取："+local.get());
        new Thread(()-> System.out.println("现在线程是："+Thread.currentThread().getName()+";在当前线程中获取："+local.get())).start();

    }
}

class CodeBearThreadLocal<T> {
    private ConcurrentHashMap<Long,T> map = new ConcurrentHashMap<Long,T>();


    public void set(T value){
        map.put(Thread.currentThread().getId(),value);
    }
    public T get(){
        return map.get(Thread.currentThread().getId());
    }
}