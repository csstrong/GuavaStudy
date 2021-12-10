package com.chensi.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/10 16:52
 ***********************************/
public class ConcreteListener extends BaseListener{
    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteListener.class);

    @Subscribe
    public void conTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [ConcreteListener],the event [{}] will be handle by {}.{}",
                event, this.getClass().getSimpleName(), "conTask");
        }
    }
}
