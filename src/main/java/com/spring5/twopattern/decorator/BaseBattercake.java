package com.spring5.twopattern.decorator;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class BaseBattercake extends Battercake {
    protected String getMsg() {
        return "煎饼";
    }

    protected int getPrice() {
        return 5;
    }
}
