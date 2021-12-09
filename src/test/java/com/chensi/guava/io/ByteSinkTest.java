package com.chensi.guava.io;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/***********************************
 * @author chensi
 * @date 2021/12/9 19:22
 ***********************************/
public class ByteSinkTest {

    private final String path = "E:\\GitDownload\\Guava\\src\\test\\resources\\target.dat";

    @Test
    public void testByteSink() throws IOException {
        File file=new File(path);
        file.deleteOnExit();
        ByteSink byteSink = Files.asByteSink(file);
        byte[] bytes={0x01,0x02};
        byteSink.write(bytes);

        byte[] expected=Files.toByteArray(file);
        assertThat(expected,is(bytes));

    }

    @Test
    public void testFromSourceToSink()throws IOException{
        String source="E:\\GitDownload\\Guava\\src\\test\\resources\\1.jpg";
        String target="E:\\GitDownload\\Guava\\src\\test\\resources\\2.jpg";
        File sourceFile=new File(source);
        File targetFile=new File(target);
        targetFile.deleteOnExit();
        ByteSource byteSource = Files.asByteSource(sourceFile);
        byteSource.copyTo(Files.asByteSink(targetFile));
        assertThat(targetFile.exists(),equalTo(true));

        String s1 = Files.asByteSource(sourceFile).hash(Hashing.sha256()).toString();
        String s2 = Files.asByteSource(targetFile).hash(Hashing.sha256()).toString();
        assertThat(s1.equals(s2),equalTo(true));
    }
}
