package com.chensi.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/10 15:10
 ***********************************/
public class SimpleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [SimpleListener],has received event [{}] and will take a action!", event);
        }
    }
}
