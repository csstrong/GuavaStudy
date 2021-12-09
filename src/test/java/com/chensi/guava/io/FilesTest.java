package com.chensi.guava.io;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/***********************************
 * @author chensi
 * @date 2021/12/9 10:42
 ***********************************/
public class FilesTest {

    private final String SOURCE_FILE = "E:\\GitDownload\\Guava\\src\\test\\resources\\source.txt";
    private final String TARGET_FILE = "E:\\GitDownload\\Guava\\src\\test\\resources\\target.txt";

    /**
     * TODO chensi will finish this in the future.
     *
     * @throws IOException
     */
    @Test
    public void testCopyFileWithGuava() throws IOException {
        File targetFile = new File(TARGET_FILE);
        Files.copy(new File(SOURCE_FILE), targetFile);
        assertThat(targetFile.exists(), equalTo(true));
    }

    @Test
    public void testCopyFileWithJDKNio2() throws IOException {
        java.nio.file.Files.copy(
            Paths.get("E:\\GitDownload\\Guava\\src\\test\\resources", "source.txt"),
            Paths.get("E:\\GitDownload\\Guava\\src\\test\\resources", "target.txt"),
            StandardCopyOption.REPLACE_EXISTING);
        File targetFile = new File(TARGET_FILE);
        assertThat(targetFile.exists(), equalTo(true));

    }

    @After
    public void tearDown() {
        File targetFile = new File(TARGET_FILE);
        if (targetFile.exists())
            targetFile.delete();
    }
}
