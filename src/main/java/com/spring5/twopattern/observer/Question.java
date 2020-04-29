package com.spring5.twopattern.observer;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class Question {
    private String userName;
    private String content;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
