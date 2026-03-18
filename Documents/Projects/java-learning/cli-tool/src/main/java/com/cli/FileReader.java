package com.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader{
    public static String readFile(String filepath) throws IOException{
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }
    public static long getFileSize(String filepath) throws IOException {
        return Files.size(Paths.get(filepath));
    }
    public static int getLineCount(String content) {
        return content.split("\n").length;
    }
}

