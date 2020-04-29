package com.spring5.twopattern.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(String str){
        //业务逻辑
        System.out.println("执行subscribe方法，传入的参数是："+str);
    }
}
