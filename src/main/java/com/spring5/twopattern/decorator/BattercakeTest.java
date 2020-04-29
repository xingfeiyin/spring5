package com.spring5.twopattern.decorator;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class BattercakeTest {
    public static void main(String[] args) {
        Battercake battercake;
        //一个煎饼
        battercake = new BaseBattercake();
        //加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //加一根肠
        battercake = new SausageDecorator(battercake);
        /**
         * 跟静态代理最大的不同就是职责不同
         * 静态代理不一定满足is-a的关系
         * 静态代理会做功能增强，使同一个职责变得不一样
         */

        System.out.println(battercake.getMsg()+",总价："+battercake.getPrice());
    }
}
