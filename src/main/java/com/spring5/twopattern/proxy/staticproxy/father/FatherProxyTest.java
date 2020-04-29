package com.spring5.twopattern.proxy.staticproxy.father;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class FatherProxyTest {
    public static void main(String[] args) {
        //只能帮儿子找对象，不能帮表妹、不能帮陌生人
        Father father = new Father(new Son());
        father.findLove();
    }
}
