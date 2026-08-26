package org.example.Concurrency;

public class HelloWorldPrinter implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello World Printer : " + Thread.currentThread().getName());
    }
}
