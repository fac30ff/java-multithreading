package com.github.fac30ff.java.multithreading.demo1;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            Random random = new Random();
            int duration = random.nextInt(4000);
            System.out.println("Starting ...");
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished.");
        });

        Future<Integer> submit = executorService.submit(() -> {
            Random random = new Random();
            int duration = random.nextInt(4000);

            if (duration > 2000) {
                throw new IOException("Sleeping for too long");
            }
            System.out.println("Starting ...");
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished.");
            return duration;
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("Result is: " + submit.get());
    }
}
