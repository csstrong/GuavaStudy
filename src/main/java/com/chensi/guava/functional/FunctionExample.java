package com.chensi.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.net.ServerSocket;


/***********************************
 * @author chensi
 * @date 2021/12/7 16:51
 ***********************************/
public class FunctionExample {


    public static void main(String[] args) throws IOException {
        Function<String, Integer> function = new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                Preconditions.checkNotNull(s, "The input Stream should not be null.");
                return null;
            }
        };

//    System.out.println(function.apply("Hello"));

        process("Hello", new Handler.LengthDoubleHandler());
        System.out.println(Functions.toStringFunction().apply(new ServerSocket(8888)));

        Function<String, Double> compose = Functions
            .compose(new Function<Integer, Double>() {

                @Override
                public Double apply(Integer integer) {
                    return integer * 1.0D;
                }
            }, new Function<String, Integer>() {
                public Integer apply(String s) {
                    return s.length();
                }
            });
        System.out.println(compose.apply("Hello"));

    }

    interface Handler<IN, OUT> {

        OUT handle(IN input);

        static class LengthDoubleHandler implements Handler<String, Integer> {

            @Override
            public Integer handle(String input) {
                return input.length() * 2;
            }
        }
    }

    private static void process(String text, Handler<String, Integer> handler) {
        System.out.println(handler.handle(text));
    }

}
