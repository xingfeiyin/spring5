package com.spring5.twopattern.strategy.promotion;


import org.apache.commons.lang3.StringUtils;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class PromotionTest {
    public static void main(String[] args) {
        method1();

        method2();

        String promotionKey = "GROUPBUY";
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStratege(promotionKey));
        promotionActivity.execute();


    }

    private static void method2() {
        PromotionActivity promotionActivity = null;
        String promotionKey = "COUPON";
        if (StringUtils.equals(promotionKey,"COUPON")){
            promotionActivity = new PromotionActivity(new CouponStrategy());
        }else if (StringUtils.equals(promotionKey,"CASHBACK")){
            promotionActivity = new PromotionActivity(new CashbackStrategy());
        }
        promotionActivity.execute();
    }

    private static void method1() {
        PromotionActivity activity618 = new PromotionActivity(new CouponStrategy());
        PromotionActivity activity1111 = new PromotionActivity(new CashbackStrategy());

        activity618.execute();
        activity1111.execute();
    }
}
