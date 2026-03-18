package com.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Write files to disk

public class FileWriter {
    public static void writeFile(String filepath, String content) throws IOException {
        Files.write(Paths.get(filepath), content.getBytes());
    }
}