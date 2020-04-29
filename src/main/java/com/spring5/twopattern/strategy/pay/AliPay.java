package com.spring5.twopattern.strategy.pay;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class AliPay extends Payment {
    public String getName() {
        return "支付宝";
    }

    protected double queryBalance(String uid) {
        return 900;
    }
}
