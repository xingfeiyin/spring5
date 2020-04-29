package com.spring5.twopattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class Teacher implements Observer {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        GPer gPer = (GPer) o;
        Question question = (Question) arg;
        System.out.println("==========================");
        System.out.println(name +"老师好\n 您收到了一个来自"+gPer.getName()+"的提问，希望您解答，内容如下：\n"
            + question.getContent() +"\n 提问者："+question.getUserName());
    }
}
