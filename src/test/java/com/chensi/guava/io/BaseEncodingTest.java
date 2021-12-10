package com.chensi.guava.io;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/***********************************
 * @author chensi
 * @date 2021/12/10 8:55
 ***********************************/
public class BaseEncodingTest {

    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
    }

    @Test
    public void testBase64Decode(){
        BaseEncoding baseEncoding=BaseEncoding.base64();
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));
    }

    @Test
    public void testMyBase64Encode(){
        System.out.println(Base64.encode("hello"));
        assertThat(Base64.encode("hello"),equalTo(BaseEncoding.base64().encode("hello".getBytes())));
        assertThat(Base64.encode("chensi"),equalTo(BaseEncoding.base64().encode("chensi".getBytes())));
        assertThat(Base64.encode("java"),equalTo(BaseEncoding.base64().encode("java".getBytes())));
        assertThat(Base64.encode("habi"),equalTo(BaseEncoding.base64().encode("habi".getBytes())));
    }
}
