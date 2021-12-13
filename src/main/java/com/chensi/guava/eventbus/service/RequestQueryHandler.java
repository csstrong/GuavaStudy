package com.chensi.guava.eventbus.service;

import com.chensi.guava.eventbus.events.Request;
import com.chensi.guava.eventbus.events.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/13 10:09
 ***********************************/
public class RequestQueryHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestQueryHandler.class);

    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    @Subscribe
    public void doQuery(Request request) {
        LOGGER.info("start query the orderNo [{}]", request.toString());
        Response response = new Response();
        this.eventBus.post(response);
    }
}
