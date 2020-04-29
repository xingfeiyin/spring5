package com.spring5.twopattern.singleton.threadlocal;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 *  ThreadLocal
 **/
public class ThreadLocalSingleton {

    private static final  ThreadLocal<ThreadLocalSingleton> threadLocalInstance =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
