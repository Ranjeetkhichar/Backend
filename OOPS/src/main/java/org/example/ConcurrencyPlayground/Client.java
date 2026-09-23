package org.example.ConcurrencyPlayground;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends Thread {
    public static void main(String[] args) {

        System.out.println("Starting Client with thread " + Thread.currentThread().getName());
        PrintNos printer = new PrintNos(4);
        printer.run();

        Thread t1 = new Thread(printer);
        t1.start();

//        for(int i = 1; i <= 10000000 ; i++) {
//            PrintNos print = new PrintNos(i);
//            Thread t2 = new Thread(print);
//            t2.start();
//        }

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for(int i = 1 ; i <= 10000000 ; i++) {
            PrintNos print = new PrintNos(i);
            executor.execute(print);
        }

    }
}
