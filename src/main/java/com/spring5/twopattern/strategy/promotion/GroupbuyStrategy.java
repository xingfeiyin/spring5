package com.spring5.twopattern.strategy.promotion;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class GroupbuyStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("拼团促销");
    }
}
