package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.listeners.SimpleListener;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/10 15:52
 ***********************************/
public class SimpleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the simple event.");
        eventBus.post("Simple Event");
    }

}
