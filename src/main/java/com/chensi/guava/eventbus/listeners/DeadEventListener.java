package com.chensi.guava.eventbus.listeners;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/***********************************
 * @author chensi
 * @date 2021/12/13 9:40
 ***********************************/
public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event){
        //can know where the source from
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
