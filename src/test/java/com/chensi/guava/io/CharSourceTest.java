package com.chensi.guava.io;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/***********************************
 * @author chensi
 * @date 2021/12/9 16:49
 ***********************************/
public class CharSourceTest {

    @Test
    public void testCharSourceWrap() throws IOException {
        CharSource charSource = CharSource.wrap("i am the CharSource.");
        String resultAsRead = charSource.read();
        assertThat(resultAsRead, equalTo("i am the CharSource."));
        ImmutableList<String> list = charSource.readLines();
        assertThat(list.size(), equalTo(1));
        assertThat(charSource.length(), equalTo(20L));
    }

    @Test
    public void testConcat() throws IOException {
        CharSource charSource = CharSource.concat(
            CharSource.wrap("i am the CharSourc1\n"),
            CharSource.wrap("i am the CharSourc2")
        );

        charSource.lines().forEach(System.out::println);
    }
}
