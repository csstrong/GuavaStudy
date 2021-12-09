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

        final String expectedString = "　　壬戌之秋，七月既望，苏子与客泛舟，游于赤壁之下。清风徐来，水波不兴。举酒属客，诵明月之诗，歌窈窕之章。少焉，月出于东山之上，徘徊于斗牛之间。白露横江，水光接天。纵一苇之所如，凌万顷之茫然。浩浩乎如冯虚御风，而不知其所止；飘飘乎如遗世独立，羽化而登仙。(冯 通：凭)\n" +
            "\n" +
            "　　于是饮酒乐甚，扣舷而歌之。歌曰：“桂棹兮兰桨，击空明兮溯流光。渺渺兮予怀，望美人兮天一方。”客有吹洞箫者，倚歌而和之。其声呜呜然，如怨如慕，如泣如诉；余音袅袅，不绝如缕。舞幽壑之潜蛟，泣孤舟之嫠妇。\n" +
            "\n" +
            "　　苏子愀然，正襟危坐，而问客曰：“何为其然也？”客曰：“‘月明星稀，乌鹊南飞。’此非曹孟德之诗乎？西望夏口，东望武昌，山川相缪，郁乎苍苍，此非孟德之困于周郎者乎？方其破荆州，下江陵，顺流而东也，舳舻千里，旌旗蔽空，酾酒临江，横槊赋诗，固一世之雄也，而今安在哉？况吾与子渔樵于江渚之上，侣鱼虾而友麋鹿，驾一叶之扁舟，举匏樽以相属。寄蜉蝣于天地，渺沧海之一粟。哀吾生之须臾，羡长江之无穷。挟飞仙以遨游，抱明月而长终。知不可乎骤得，托遗响于悲风。”\n" +
            "\n" +
            "　　苏子曰：“客亦知夫水与月乎？逝者如斯，而未尝往也；盈虚者如彼，而卒莫消长也。盖将自其变者而观之，则天地曾不能以一瞬；自其不变者而观之，则物与我皆无尽也，而又何羡乎！且夫天地之间，物各有主，苟非吾之所有，虽一毫而莫取。惟江上之清风，与山间之明月，耳得之而为声，目遇之而成色，取之无禁，用之不竭。是造物者之无尽藏也，而吾与子之所共适。”(共适 一作：共食)\n" +
            "\n" +
            "　　客喜而笑，洗盏更酌。肴核既尽，杯盘狼籍。相与枕藉乎舟中，不知东方之既白。";
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
        //深度优先的先序遍历
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesPostOrderTraversal(){
        File root=new File("E:\\GitDownload\\Guava\\src\\main");
        //深度优先后续遍历
        Iterable<File> files = Files.fileTraverser().depthFirstPostOrder(root);
        files.forEach(System.out::println);
    }

    @Test
    public void testTreeFilesBreadOrderTraversal(){
        File root=new File("E:\\GitDownload\\Guava\\src\\main");
        //广度优先遍历
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
