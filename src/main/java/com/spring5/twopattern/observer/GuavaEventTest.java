package com.spring5.twopattern.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class GuavaEventTest {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("Tom");
    }
}
