package com.chensi.guava.utilities;


import ch.qos.logback.classic.Logger;
import com.google.common.base.Stopwatch;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/***********************************
 * @author chensi
 * @date 2021/12/8 10:50
 ***********************************/
public class StopWatchExample {

    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(StopWatchExample.class);

    public static void main(String[] args) throws InterruptedException {
        process("2354615566");
    }

    private static void process(String orderNo) throws InterruptedException {

        LOGGER.info("Start process the order [{}]", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MICROSECONDS.sleep(100);
        LOGGER.info("The orderNo [{}] process successful and elapsed [{}]", orderNo,
            stopwatch.stop());
    }
}
