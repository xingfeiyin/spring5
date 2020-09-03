package com.spring5.juc.aqs;

/**
 * @author yinxf
 * @Date 2020/9/3
 * @Description
 **/
public class ProducerConsumerMain {
    public static void main(String[] args) {
        ProducerConsumer resource = new ProducerConsumer();

        new Thread(()->{
            while (true){
                resource.product();
            }
        }).start();

        new Thread(()->{
            while (true){
                resource.consume();
            }
        }).start();
    }
}
