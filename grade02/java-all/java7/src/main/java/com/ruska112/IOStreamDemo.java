package com.ruska112;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class IOStreamDemo {
    public static void writeIntArrayToOutputStream(OutputStream outputStream, int[] arr) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            for (int num : arr) {
                dataOutputStream.writeInt(num);
            }
        }
    }

    public static int[] readIntArrayFromInputStream(InputStream inputStream, int[] arr) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = dataInputStream.readInt();
            }
            return arr;
        }
    }

    public static void writeIntArrayToWriter(Writer writer, int[] arr) throws IOException {
        try (var printWriter = new PrintWriter(writer)) {
            for (int i = 0; i < arr.length; i++) {
                printWriter.write(String.valueOf(arr[i]));
                if (i < arr.length - 1) {
                    printWriter.write(" ");
                }
            }
        }
    }

    public static int[] readIntArrayFromReader(Reader reader, int[] arr) throws IOException {
        try (var stringReader = new BufferedReader(reader)) {
            String[] strs = stringReader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(strs[i]);
            }
            return arr;
        }
    }

    public static int[] readIntArrayFromFileWithOffset(String filename, int[] arr, int offset) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "r")) {
            if (offset + arr.length >= randomAccessFile.length()) {
                throw new IllegalArgumentException();
            }
            randomAccessFile.seek(offset * 2L);
            String[] strs = randomAccessFile.readLine().split(" ");
            for (int i = 0; i < arr.length && i < strs.length; i++) {
                arr[i] = Integer.parseInt(strs[i]);
            }
            return arr;
        }
    }

    public static ArrayList<File> getAllFilesWithExtension(String dirName, String extension) {
        File[] files = new File(dirName).listFiles(File::isFile);
        if (files == null) {
            throw new IllegalArgumentException("Dir doesn't contain any files!");
        }
        return Arrays.stream(files).filter(x -> x.getName().endsWith("." + extension)).sorted().map(x -> {
            try {
                return new File(x.getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<File> getAllFilesWithExtensionInSubdirectories(String dirName, String extension) throws IOException {
        File[] files = new File(dirName).listFiles(File::isFile);
        File[] dirs = new File(dirName).listFiles(File::isDirectory);
        ArrayList<File> result = new ArrayList<>();
        if (files != null && Arrays.stream(files).anyMatch(x -> x.getAbsolutePath().endsWith("." + extension))) {
            result.addAll(Arrays.stream(files).filter(x -> x.getAbsolutePath().endsWith("." + extension)).sorted().map(x -> {
                try {
                    return new File(x.getCanonicalPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).toList());
        }
        if (dirs != null) {
            for (File dir : dirs) {
                result.addAll(getAllFilesWithExtensionInSubdirectories(dir.getCanonicalPath(), extension));
            }
        }
        return result;
    }
}
