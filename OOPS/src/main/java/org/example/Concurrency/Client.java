package org.example.Concurrency;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {

        System.out.println("Hi, my current thread is : " + Thread.currentThread().getName());
        HelloWorldPrinter printer = new HelloWorldPrinter();

        Thread thread = new Thread(printer);
        thread.start();

        printer.run();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(int i = 1; i <= 100; i++) {
            int finalI = i;
            int finalI1 = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " print no " + finalI1);
                }
            });
        }

    }
}
