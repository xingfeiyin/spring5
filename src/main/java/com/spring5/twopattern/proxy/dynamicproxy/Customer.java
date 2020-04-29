package com.spring5.twopattern.proxy.dynamicproxy;

import com.spring5.twopattern.proxy.staticproxy.father.Person;

/**
 * @author yinxf
 * @date 2020-04-11
 * 创建单身客户类
 */
public class Customer implements Person {
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180");
        System.out.println("有腹肌");
    }
}
