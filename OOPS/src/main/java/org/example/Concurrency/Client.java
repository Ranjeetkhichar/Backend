package org.example.Concurrency;


import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {

        System.out.println("Hi, my current thread is : " + Thread.currentThread().getName());
        HelloWorldPrinter printer = new HelloWorldPrinter();

        Thread thread = new Thread(printer);
        thread.start();

        printer.run();

        long startTime = System.currentTimeMillis();
//        print 1 to 100 with each no should print by new thread.
        for(int i = 1; i <= 10000000; i++){  //check activity monitor
            PrintNos printeNo = new PrintNos(i);
            Thread newthread = new Thread(printeNo);
            newthread.start();
        }

        System.out.println("Task assignment done");

        long endTimewithNewThreads = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 1; i <= 10000000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " print no " + finalI);
                }
            });
        }
        long endTimewithExecutorService = System.currentTimeMillis();

        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 1; i <= 10000000; i++) {
            int finalI = i;
            es.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " print no " + finalI);
                }
            });
        }

        long endTimewithES = System.currentTimeMillis();

        System.out.println("Time taken in threads " + (endTimewithNewThreads - startTime) +
                " vs Time taken in executor service" + (endTimewithExecutorService - endTimewithNewThreads) +
                " vs Time taken in es" + (endTimewithES - endTimewithExecutorService));

    }
}
