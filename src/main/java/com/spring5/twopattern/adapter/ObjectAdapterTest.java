package com.spring5.twopattern.adapter;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class ObjectAdapterTest {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}
