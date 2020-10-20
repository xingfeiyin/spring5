package com.spring5.java.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author yinxf
 * @Date 2020/10/16
 * @Description java引用
 **/
public class Reference {

    public static void main(String[] args) {

        //软引用
//        testSoftReference();
        //弱引用
        testWeakReference();

    }

    private static void testWeakReference() {
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(weakReference.get());
    }

    private static void testSoftReference() {
        //内存够用，软引用对应不会回收
//        memoryEnough();

        //内存不够用，会回收软引用对象
        notEnough();
    }

    private static void notEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        try {
            byte[] bytes = new byte[20*1024*1024];

        }finally {
            System.out.println(obj1);
            System.out.println(softReference.get());
        }
    }

    private static void memoryEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(softReference.get());
    }

}
