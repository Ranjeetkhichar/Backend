package org.example.DesginPatterns.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends Thread {
    public static void main(String[] args) {
        //    DBConnection db1 = new DBConnection(); DBConnectin has private Constructor

        System.out.println("Debug");

        ExecutorService executor = Executors.newCachedThreadPool();
        // It doesn't ever make a task wait, if all threads are busy it will create new thread
        // What if it create infinite => There should be an limit ???

        for (int i = 0; i < 1000; i++) {
            executor.execute(new DBConnectionCreation());
        }

        System.out.println("Debug");
    }
}
