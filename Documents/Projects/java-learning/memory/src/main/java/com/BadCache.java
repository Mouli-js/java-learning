package com;

import java.util.List;
import java.util.ArrayList;

public class BadCache {
    private static List<byte[]> cache = new ArrayList<>();

    public static void addToCache(byte[] data){
        cache.add(data);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Memory Leak sample");

        Runtime runtime = Runtime.getRuntime();

        for (int iteration = 0; iteration < 100_000; iteration++) {
            byte[] data = new byte[1024]; // 1KB
            addToCache(data);
        }

        System.gc();
        Thread.sleep(100);

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory: " + (usedMemory / 1024 / 1024) + " MB");
        System.out.println("Cache size: " + cache.size());

        System.out.println("Notice: Memory keeps growing!");
        System.out.println("Cache never empties = MEMORY LEAK");
    }
}


