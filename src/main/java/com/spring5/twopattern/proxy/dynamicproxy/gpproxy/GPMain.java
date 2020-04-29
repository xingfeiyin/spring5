package com.spring5.twopattern.proxy.dynamicproxy.gpproxy;

import com.spring5.twopattern.proxy.dynamicproxy.Customer;
import com.spring5.twopattern.proxy.staticproxy.father.Person;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 **/
public class GPMain {
    public static void main(String[] args) {
        try {
            Person obj = (Person) new GPMeiPo().getInstance(new Customer());
            System.out.println(obj.getClass());
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
