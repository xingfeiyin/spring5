package com.spring5.twopattern.proxy.staticproxy.father;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class Son implements Person {

    public void findLove() {
        //我没有时间，工作忙
        System.out.println("儿子要求：肤白貌美发长腿");
    }
}
