package com.github.fac30ff.java.multithreading.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        for (int i = 0; i < 100; i++) {
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All tasks submitted");
        executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("All tasks completed");
    }
}
