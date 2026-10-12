package org.example.Exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;

public class Client {
    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//        try {
//            int ans = calculator.divide(10, 0);
//            System.out.println(ans);
//        }
//        catch (ArithmeticException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace(System.out);
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
        try {
            FileReader file = new FileReader("example.txt");
            System.out.println("File found.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task Completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        });

        t1.start();

        try {
            Thread.sleep(500);
            t1.interrupt();
            System.out.println("Task Interrupted");
        } catch (InterruptedException e) {
           System.out.println(e.getMessage());
        }

        Properties p = new Properties();
        p.getProperty("abc", "");


    }
}
