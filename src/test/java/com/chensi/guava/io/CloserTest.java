package com.chensi.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/***********************************
 * @author chensi
 * @date 2021/12/9 19:57
 ***********************************/
public class CloserTest {

    @Test
    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("E:\\GitDownload\\Guava\\src\\test\\resources\\1.jpg"));
        byteSource.openStream();

        Closer closer = Closer.create();
        //try (InputStream inputStream=byteSource.openStream()){

        try {
            InputStream inputStream = closer.register(byteSource.openStream());

        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

    //fially,this function can throw RuntimeException
    @Test(expected = RuntimeException.class)
    public void testTryCatchFinally() {
        try {
            System.out.println("work area.");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("exception area.");
            throw new RuntimeException();
        } finally {
            System.out.println("finally area");
        }
    }

    @Test
    public void testTryCatch() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            //close
            //RuntimeException runtimeException = new RuntimeException("2");
            //runtimeException.addSuppressed(t);
            //throw runtimeException;
            try {
                throw new RuntimeException("2");
            } catch (Exception e) {
                t.addSuppressed(e);
            }
        }
    }
}
