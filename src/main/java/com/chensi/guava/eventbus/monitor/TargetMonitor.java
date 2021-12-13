package com.chensi.guava.eventbus.monitor;

/***********************************
 * @author chensi
 * @date 2021/12/13 11:08
 ***********************************/
public interface TargetMonitor {
    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;
}
