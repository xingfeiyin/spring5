package com.spring5.twopattern.strategy.promotion;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class CashbackStrategy implements PromotionStrategy {

    public void doPromotion() {
        System.out.println("返现促销");
    }
}
