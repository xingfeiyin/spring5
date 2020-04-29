package com.spring5.twopattern.observer;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class ObserverTest {
    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("mic");

        gPer.addObserver(tom);
        gPer.addObserver(mic);

        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用于那些场景？");

        gPer.publishQuestion( question);
    }
}
