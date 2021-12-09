package com.chensi.service;

/***********************************
 * @author chensi
 * @date 2021/12/9 9:36
 ***********************************/
public class SimpleService implements Service {

    @Override
    public void show() {
        System.out.println("hi,i come from ther service loader.");
    }
}
