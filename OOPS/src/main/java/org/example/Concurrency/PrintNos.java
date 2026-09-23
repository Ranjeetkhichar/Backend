package org.example.Concurrency;

public class PrintNos implements Runnable {
    int no;
    public PrintNos(int no) {
        this.no = no;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(Thread.currentThread().getName() + " prints no : " + no);
    }
}
