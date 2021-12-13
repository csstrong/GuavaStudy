package com.chensi.guava.eventbus;

import com.chensi.guava.eventbus.service.QueryService;
import com.chensi.guava.eventbus.service.RequestQueryHandler;
import com.google.common.eventbus.EventBus;

/***********************************
 * @author chensi
 * @date 2021/12/13 10:15
 ***********************************/
public class ComEachOtherEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        eventBus.register(new RequestQueryHandler(eventBus));
        queryService.query("order:898876");
    }
}
