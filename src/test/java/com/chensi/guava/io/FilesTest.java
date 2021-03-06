package com.chensi.guava.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.*;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testMoveFile() throws IOException {

        try {
            //prepare for data.

            Files.move(new File(SOURCE_FILE), new File(TARGET_FILE));
            assertThat(new File(TARGET_FILE).exists(), equalTo(true));
            assertThat(new File(SOURCE_FILE).exists(), equalTo(false));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Files.move(new File(TARGET_FILE), new File(SOURCE_FILE));
        }
    }

    @Test
    public void testToString() throws IOException {

        final String expectedString = "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????(??? ?????????)\n" +
            "\n" +
            "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n" +
            "\n" +
            "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n" +
            "\n" +
            "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????(?????? ???????????????)\n" +
            "\n" +
            "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????";
        List<String> strings = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String result = Joiner.on("\n").join(strings);
        assertThat(result, equalTo(expectedString));
    }

    @Test
    public void testToProcessString() throws IOException {

        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {

            private List<Integer> lengthList = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                if (line.length() == 0) return false;
                lengthList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };
        List<Integer> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).
            readLines(lineProcessor);
        System.out.println(result);
    }

    @Test
    public void testFileMd5() throws IOException {
        File file = new File(SOURCE_FILE);
        HashCode hashCode = Files.asByteSource(file).hash(Hashing.goodFastHash(128));
        System.out.println(hashCode);
    }

    @Test
    public void testFileWrite() throws IOException {
        String testPath = "E:\\GitDownload\\Guava\\src\\test\\resources\\testFileWrite.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        String content1 = "hello world";
        Files.asCharSink(testFile, Charsets.UTF_8).write(content1);
        CharSource charSource = Files.asCharSource(testFile, Charsets.UTF_8);
        String actully = charSource.read();
        assertThat(actully, equalTo(content1));
        //System.out.println(charSource.read());
    }

    @Test
    public void testFileAppend() throws IOException {
        String testPath = "E:\\GitDownload\\Guava\\src\\test\\resources\\testFileAppend.txt";
        File testFile = new File(testPath);
        testFile.deleteOnExit();
        String content1 = "content1";
        CharSink charSink = Files.asCharSink(testFile, Charsets.UTF_8, FileWriteMode.APPEND);
        charSink.write(content1);
        String actully = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actully, equalTo(content1));

        String content2 = "content2";
        charSink.write(content2);
        actully = Files.asCharSource(testFile, Charsets.UTF_8).read();
        assertThat(actully, equalTo(content1 + content2));
    }

    @Test
    public void testTouchFile() throws IOException {
        String testPath = "E:\\GitDownload\\Guava\\src\\test\\resources\\testTouch.txt";
        File touchFile = new File(testPath);
        touchFile.deleteOnExit();
        Files.touch(touchFile);
        assertThat(touchFile.exists(), equalTo(true));
    }

    @Test
    public void testRecusive() {
        List<File> list = new ArrayList<>();
        this.recursiveList(new File("E:\\GitDownload\\Guava\\src\\main"), list);
        list.forEach(System.out::println);
    }

    private void recursiveList(File root, List<File> fileList) {
        //if (root.isHidden())
        //    return;
        //fileList.add(root);
        //if (!root.isFile()) {
        //    File[] files = root.listFiles();
        //    for (File f : files) {
        //        recursiveList(f, fileList);
        //    }
        //}

        if (root.isHidden()) return;
        if (root.isFile())
            fileList.add(root);
        else {
            File[] files = root.listFiles();
            for (File f : files) {
                recursiveList(f, fileList);
            }
        }
    }

    @Test
    public void testTreeFile(){
        File root=new File("E:\\GitDownload\\Guava\\src\\main");
        //???????????????????????????
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesPostOrderTraversal(){
        File root=new File("E:\\GitDownload\\Guava\\src\\main");
        //????????????????????????
        Iterable<File> files = Files.fileTraverser().depthFirstPostOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesBreadOrderTraversal(){
        File root=new File("E:\\GitDownload\\Guava\\src\\main");
        //??????????????????
        Iterable<File> files = Files.fileTraverser().breadthFirst(root);
        files.forEach(System.out::println);
    }

    @After
    public void tearDown() {
        File targetFile = new File(TARGET_FILE);
        if (targetFile.exists())
            targetFile.delete();
    }
}
