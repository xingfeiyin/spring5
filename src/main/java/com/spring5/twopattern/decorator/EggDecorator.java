package com.spring5.twopattern.decorator;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class EggDecorator extends BattercakeDecorator {
    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    protected void doSomething() {

    }

    protected String getMsg(){
        return super.getMsg()+"+1个鸡蛋";
    }

    protected int getPrice(){
        return super.getPrice() + 1;
    }
}
