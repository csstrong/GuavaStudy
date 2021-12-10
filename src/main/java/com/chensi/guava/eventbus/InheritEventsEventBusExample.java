package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.events.Apple;
import com.chensi.guava.eventbus.events.Fruit;
import com.chensi.guava.eventbus.listeners.FruitEaterListener;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/10 16:02
 ***********************************/
public class InheritEventsEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));
        System.out.println("====================");
        eventBus.post(new Fruit("apple"));
    }
}
