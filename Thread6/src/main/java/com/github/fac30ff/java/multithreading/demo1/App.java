package com.github.fac30ff.java.multithreading.demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        for (int i = 0; i < 3; i++) {
            executor.submit(new Processor(latch));
        }
        latch.await();
        System.out.println("Completed.");
    }
}
