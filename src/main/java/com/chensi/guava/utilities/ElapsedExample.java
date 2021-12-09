package com.chensi.guava.utilities;

import java.util.concurrent.TimeUnit;

/***********************************
 * @author chensi
 * @date 2021/12/8 10:07
 ***********************************/
public class ElapsedExample {

    public static void main(String[] args) throws InterruptedException {
        process("2354615566");
    }

    private static void process(String orderNo) throws InterruptedException {

        System.out.printf("Start process the order %s\n", orderNo);
        long startNano = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo %s process successful and elapsed %d ns.\n", orderNo,
            (System.nanoTime() - startNano));
    }
}
