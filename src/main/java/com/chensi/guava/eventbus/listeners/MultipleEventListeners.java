package com.chensi.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/10 15:58
 ***********************************/
public class MultipleEventListeners {
    private final static Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [MultipleEventListeners],the event [{}] received and will " +
                "take a action by ==task1==!", event);
        }
    }

    @Subscribe
    public void task2(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [MultipleEventListeners],the event [{}] received and will " +
                "take a action by ==task2==!", event);
        }
    }

    @Subscribe
    public void intTask(Integer event){
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [MultipleEventListeners],the event [{}] received and will " +
                "take a action by ==intTask==!", event);
        }
    }
}
