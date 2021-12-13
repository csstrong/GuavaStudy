package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.listeners.ExceptionListener;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/13 9:18
 ***********************************/
public class ExceptionEventBusExample {
    public static void main(String[] args) {
        //final EventBus eventBus = new EventBus(new ExceptionHandler());
        final EventBus eventBus = new EventBus(((exception, context) -> {
            System.out.println(context.getEvent());//"exception event"
            System.out.println(context.getEventBus());//"default"
            System.out.println(context.getSubscriber());//"ExceptionListener"
            System.out.println(context.getSubscriberMethod());//""
        }));

        eventBus.register(new ExceptionListener());
        eventBus.post("exception post");
    }

    /*
    When eventbus post message,once the subscriber generate a exception,the eventbus
        (Be equivalent to 'message queue')not handle,the exception should be catch by subscriber.
     */
/*    static class ExceptionHandler implements SubscriberExceptionHandler{

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            //getEvent()/getEventBus()/getSubscriber()/getSubscriberMethod()
            System.out.println(context.getEvent());//"exception event"
            System.out.println(context.getEventBus());//"default"
            System.out.println(context.getSubscriber());//"ExceptionListener"
            System.out.println(context.getSubscriberMethod());//""
        }
    }*/
}
