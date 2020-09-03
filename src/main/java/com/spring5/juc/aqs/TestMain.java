package com.spring5.juc.aqs;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description
 **/
public class TestMain {
    public static void main(String[] args) {
        Test test = new Test();

        new Thread(()->{
            test.add();
        }).start();

        new Thread(()->{
            test.add();
        }).start();
    }
}
