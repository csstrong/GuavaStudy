package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.listeners.ConcreteListener;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/10 16:02
 ***********************************/
public class InheritListenersEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("I am string event");
        System.out.println("post the int event");
        eventBus.post(100);
    }
}
