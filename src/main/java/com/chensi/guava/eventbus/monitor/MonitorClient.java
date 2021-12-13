package com.chensi.guava.eventbus.monitor;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/***********************************
 * @author chensi
 * @date 2021/12/13 16:01
 ***********************************/

/**
 * tail
 * Apache Flume1.7 Spooling
 * <p>
 * .position
 */
public class MonitorClient {
    public static void main(String[] args) throws Exception {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());

        TargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "E:\\tmp\\monitor");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                monitor.stopMonitor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);
        executorService.shutdown();
        monitor.startMonitor();
    }
}

