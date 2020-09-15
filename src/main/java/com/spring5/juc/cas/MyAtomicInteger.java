package com.spring5.juc.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yinxf
 * @Date 2020/9/7
 * @Description
 **/
public class MyAtomicInteger {

    private volatile int value ;

    private static long offset ;//便宜地址

    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            unsafe = (Unsafe) theUnsafeField.get(null);
            Field fi = MyAtomicInteger.class.getDeclaredField("value");
            offset = unsafe.objectFieldOffset(fi); //获得便宜地址
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void increment(int num){
        int tempValue;
        do {
            tempValue = unsafe.getIntVolatile(this,offset);  //获取值
        }while (!unsafe.compareAndSwapInt(this,offset,tempValue,value+num)); //CAS自旋
    }

    public int get(){
        return value;
    }
}
