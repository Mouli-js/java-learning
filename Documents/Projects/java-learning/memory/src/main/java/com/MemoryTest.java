package com.memory;

import java.util.ArrayList;
import java.util.List;
import java.lang.Runtime;

public class MemoryTest {
    public static void main(String[] args){
        System.out.print("Memory and GC demo");

        Runtime runtime = Runtime.getRuntime();
        long beforememory = runtime.totalMemory() - runtime.freeMemory();
        System.out.print("Initial Memory: " + formatMemory(beforememory));

        // creating 1 million objs
        List <byte []> garbage = new ArrayList<>();
        for(int i = 0; i < 1_00_000; i++){
            byte [] data = new byte[1024]; // 1kb each
            garbage.add(data);
        }

        long afterCreation = runtime.totalMemory() - runtime.freeMemory();
        System.out.print("Memory after creation " + formatMemory(afterCreation));
        System.out.print("Used: " + formatMemory(afterCreation - beforememory));

        //making them garbage
        garbage = null;

        // forcing garbage collection
        System.gc();

        //waiting for gc to complete
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long afterGC = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory after GC: " + formatMemory(afterGC));
        System.out.println("Freed: " + formatMemory(afterCreation - afterGC));

    }
    private static String formatMemory(long bytes){
        return (bytes/1024/1024) + "MB";
    }
}