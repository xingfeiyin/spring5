package com.spring5.twopattern.decorator;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public abstract class BattercakeDecorator extends Battercake{
    //静态代理，委派
    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }
    protected abstract void doSomething();

    protected String getMsg(){
        return this.battercake.getMsg();
    }

    protected int getPrice(){
        return this.battercake.getPrice();
    }
}
