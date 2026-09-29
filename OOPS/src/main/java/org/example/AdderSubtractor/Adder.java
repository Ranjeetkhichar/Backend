package org.example.AdderSubtractor;

import java.util.concurrent.Callable;

//public class Adder implements Callable<Void> {
public class Adder implements Runnable {

    private Value val;

    public Adder(Value val) {
        this.val = val;
    }

//    @Override
//    public void call() {
//        for(int i = 1 ; i <= 1000000 ; i++){
//            val.value += i;
//        }
//        return  null;
//    }

    @Override
    public void run() {
        for(int i = 1 ; i <= 1000000 ; i++){
            val.value += i;
        }
    }
}
