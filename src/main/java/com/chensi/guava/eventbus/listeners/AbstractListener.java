package com.chensi.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/10 16:13
 ***********************************/
public class AbstractListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractListener.class);

    @Subscribe
    public void commonTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("This is [AbstractListener],the event [{}] will be handle by {}.{}",
                event,this.getClass().getSimpleName(),"commonTask");
        }
    }
}
