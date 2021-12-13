package com.chensi.guava.eventbus.events;

import com.google.common.base.MoreObjects;

/***********************************
 * @author chensi
 * @date 2021/12/13 9:59
 ***********************************/
public class Request {
    private final String orderNo;

    public Request(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("orderNo", orderNo)
            .toString();
    }
}
