package com.github.fac30ff.java.multithreading.demo1;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();
        Thread t1 = new Thread(processor::produce);
        Thread t2 = new Thread(processor::consume);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
