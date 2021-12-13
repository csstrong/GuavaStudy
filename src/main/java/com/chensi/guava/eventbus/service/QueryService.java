package com.chensi.guava.eventbus.service;

import com.chensi.guava.eventbus.events.Request;
import com.chensi.guava.eventbus.events.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***********************************
 * @author chensi
 * @date 2021/12/13 10:02
 ***********************************/
//As subscriber and service interface
public class QueryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    public void query(String orderNo) {
        LOGGER.info("Received the orderNo [{}]", orderNo);
        this.eventBus.post(new Request(orderNo));
    }

    @Subscribe
    public void handleResponse(Response response){
        LOGGER.info("[{}]",response);
    }
}
