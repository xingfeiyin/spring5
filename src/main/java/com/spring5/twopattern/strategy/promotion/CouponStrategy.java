package com.spring5.twopattern.strategy.promotion;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class CouponStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("领取优惠券，直接抵扣");
    }
}
