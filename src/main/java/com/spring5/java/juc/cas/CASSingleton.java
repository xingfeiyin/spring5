package com.spring5.java.juc.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yinxf
 * @Date 2020/9/7
 * @Description CAS与单例模式
 **/
public class CASSingleton {
    private CASSingleton(){}

    private static AtomicReference<CASSingleton> singletonAtomicReference = new AtomicReference<>();

    public static CASSingleton getInstance(){
        while (true){
            CASSingleton singleton = singletonAtomicReference.get(); //获取singleton
            if (singleton != null){   //如果不为空，直接返回
                return singleton;
            }

            //如果为空，创建singleton
            singleton = new CASSingleton();

            //CAS操作，预期值是null，新值是singleton；成功返回singleton ，失败进入第二次循环，singletonAtomicReference.get()就不会为空
            if (singletonAtomicReference.compareAndSet(null,singleton)){
                return singleton;
            }

        }


    }
}
