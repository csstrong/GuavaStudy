package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.listeners.DeadEventListener;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/13 9:42
 ***********************************/
public class DeadEventBusExample {
    public static void main(String[] args) {
        DeadEventListener deadEventListener = new DeadEventListener();
        final EventBus eventBus = new EventBus("DeadEvent") {
            @Override
            public String toString() {
                return "DEAD-EVENT_BUS.";
            }
        };
        eventBus.register(deadEventListener);
        eventBus.post("Hello");
        eventBus.post("Hello");

        eventBus.unregister(deadEventListener);
        eventBus.post("Hello");
        eventBus.post("Hello");
        eventBus.post("Hello");

    }
}
