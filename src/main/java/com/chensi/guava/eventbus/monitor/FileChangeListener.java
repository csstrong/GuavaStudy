package com.chensi.guava.eventbus.monitor;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/13 15:56
 ***********************************/
public class FileChangeListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileChangeListener.class);

    @Subscribe
    public void onChange(FileChangeEvent event) {
        LOGGER.info("{} - {}", event.getPath(), event.getKind());
    }
}
