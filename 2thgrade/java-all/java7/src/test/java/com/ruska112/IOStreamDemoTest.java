package com.ruska112;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static com.ruska112.IOStreamDemo.*;

public class IOStreamDemoTest {
    @Test
    public void writeIntArrayToOutputStreamTest0() throws IOException {
        try (var outputStream = new FileOutputStream("test.bin")) {
            writeIntArrayToOutputStream(outputStream, new int[]{0, 1, 2, 3});
            var inputStream = new FileInputStream("test.bin");
            int[] arr = new int[4];
            assertArrayEquals(new int[]{0, 1, 2, 3}, readIntArrayFromInputStream(inputStream, arr));
        }
    }

    @Test
    public void writeIntArrayToOutputStreamTest12241() throws IOException {
        try (var outputStream = new ByteArrayOutputStream()) {
            writeIntArrayToOutputStream(outputStream, new int[]{0, 1, 2, 3});
            var inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            int[] arr = new int[4];
            assertArrayEquals(new int[]{0, 1, 2, 3}, readIntArrayFromInputStream(inputStream, arr));
        }
    }

    @Test
    public void readIntArrayFromInputStreamTest0() throws IOException {
        try (var bs = new FileInputStream("test.bin")) {
            try (var outputStream = new FileOutputStream("test.bin")) {
                writeIntArrayToOutputStream(outputStream, new int[]{0, 1, 2, 3});
            }
            int[] resultArray = new int[]{0, 0, 0, 0};
            readIntArrayFromInputStream(bs, resultArray);
            assertArrayEquals(new int[]{0, 1, 2, 3}, resultArray);
        }
    }

    @Test
    public void writeIntArrayToWriterTest12241() throws IOException {
        try (var cs = new StringWriter()) {
            writeIntArrayToWriter(cs, new int[]{1, 17, 178, 17809});
            var reader = new StringReader(cs.toString());
            int[] arr = new int[4];
            assertArrayEquals(new int[]{1, 17, 178, 17809}, readIntArrayFromReader(reader, arr));
        }
    }

    @Test
    public void writeIntArrayToWriterTest0() throws IOException {
        try (var cs = new CharArrayWriter()) {
            writeIntArrayToWriter(cs, new int[]{0, 1, 2, 3});
            var reader = new CharArrayReader(cs.toCharArray());
            int[] arr = new int[4];
            assertArrayEquals(new int[]{0, 1, 2, 3}, readIntArrayFromReader(reader, arr));
        }
    }

    @Test
    public void readIntArrayFromReaderTest0() throws IOException {
        try (var cs = new StringReader("0 1 2 3")) {
            int[] resultArray = new int[]{0, 0, 0, 0};
            readIntArrayFromReader(cs, resultArray);
            assertArrayEquals(new int[]{0, 1, 2, 3}, resultArray);
        }
    }

    @Test
    public void readIntArrayFromFileWithOffsetTest0() throws IOException {
        try (var outputStream = new FileWriter("test.bin")) {
            writeIntArrayToWriter(outputStream, new int[]{0, 0, 0, 1, 2, 3, 4});
        }
        int[] arrResult = new int[]{0, 1, 2, 3, 4};
        int[] result = new int[5];
        assertArrayEquals(arrResult, readIntArrayFromFileWithOffset("test.bin", result, 2));
    }

    @Test
    public void getAllFilenameWithExtensionTest0() {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/pom.xml"));
        assertEquals(files, getAllFilesWithExtension(".", "xml"));
    }

    @Test
    public void getAllFilenameWithExtensionTest1() {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/src/main/java/com/ruska112/IOStreamDemo.java"), new File("/home/ruska/aksur/java7/src/main/java/com/ruska112/Main.java"));
        assertEquals(files, getAllFilesWithExtension("./src/main/java/com/ruska112/", "java"));
    }

    @Test
    public void getAllFilenameWithExtensionTest2() {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/.idea/compiler.xml"), new File("/home/ruska/aksur/java7/.idea/encodings.xml"), new File("/home/ruska/aksur/java7/.idea/jarRepositories.xml"), new File("/home/ruska/aksur/java7/.idea/misc.xml"), new File("/home/ruska/aksur/java7/.idea/uiDesigner.xml"),new File("/home/ruska/aksur/java7/.idea/vcs.xml"), new File("/home/ruska/aksur/java7/.idea/workspace.xml"));
        assertEquals(files, getAllFilesWithExtension(".idea/", "xml"));
    }

    @Test
    public void getAllFilesWithExtensionInSubdirectoriesTest0() throws IOException {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/src/main/java/com/ruska112/IOStreamDemo.java"), new File("/home/ruska/aksur/java7/src/main/java/com/ruska112/Main.java"), new File("/home/ruska/aksur/java7/src/test/java/com/ruska112/IOStreamDemoTest.java"));
        assertEquals(files, getAllFilesWithExtensionInSubdirectories(".", "java"));
    }

    @Test
    public void getAllFilesWithExtensionInSubdirectoriesTest1() throws IOException {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/test.bin"));
        assertEquals(files, getAllFilesWithExtensionInSubdirectories(".", "bin"));
    }

    @Test
    public void getAllFilesWithExtensionInSubdirectoriesTest2() throws IOException {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("/home/ruska/aksur/java7/.gitignore"), new File("/home/ruska/aksur/java7/.idea/.gitignore"));
        assertEquals(files, getAllFilesWithExtensionInSubdirectories(".", "gitignore"));
    }
}