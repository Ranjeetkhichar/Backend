package org.example.Concurrency;

public class PrintNos implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " print no ");
    }
}
