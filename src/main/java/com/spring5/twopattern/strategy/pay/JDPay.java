package com.spring5.twopattern.strategy.pay;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class JDPay extends Payment {
    public String getName() {
        return "京东支付";
    }

    protected double queryBalance(String uid) {
        return 500;
    }
}
