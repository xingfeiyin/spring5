package com.spring5.twopattern.strategy.pay;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class UnionPay extends Payment {
    public String getName() {
        return "银联支付";
    }

    protected double queryBalance(String uid) {
        return 120;
    }
}
