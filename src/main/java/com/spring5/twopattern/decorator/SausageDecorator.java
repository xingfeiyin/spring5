package com.spring5.twopattern.decorator;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class SausageDecorator extends BattercakeDecorator {
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    protected void doSomething() {

    }

    protected String getMsg(){
        return super.getMsg() +"+1根香肠";
    }

    protected int getPrice(){
        return super.getPrice() + 2;
    }
}
