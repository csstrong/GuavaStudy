package com.chensi.guava.eventbus.events;

import com.google.common.base.MoreObjects;

/***********************************
 * @author chensi
 * @date 2021/12/10 17:13
 ***********************************/
public class Fruit {

    private final String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Name", name).toString();
    }



}
