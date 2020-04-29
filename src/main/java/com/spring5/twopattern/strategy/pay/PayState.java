package com.spring5.twopattern.strategy.pay;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class PayState {
    private int code ;
    private Object data;
    private String msg;

    public PayState(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public String toString(){
        return "支付状态："+code +msg +"交易详情："+data ;
    }
}
