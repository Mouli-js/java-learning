package com.cli;

public class Statistics {
    private long fileSize;
    private int lineCount;
    private int charCount;
    private boolean isValid;

    public Statistics(String content, boolean isValid) {
        this.fileSize = content.length();
        this.lineCount = content.split("\n").length;
        this.charCount = content.length();
        this.isValid = isValid;
    }

    public void print() {
        System.out.println("\nStatistics ");
        System.out.println("File size: " + fileSize + " bytes");
        System.out.println("Lines: " + lineCount);
        System.out.println("Characters: " + charCount);
        System.out.println("Valid JSON: " + (isValid ? "YES" : "NO"));
    }
}