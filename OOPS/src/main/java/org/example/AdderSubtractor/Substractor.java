package org.example.AdderSubtractor;

import java.util.concurrent.Callable;

//public class Substractor implements Callable<Void> {
public class Substractor implements Runnable {

    private Value val;
    public Substractor(Value val) {
        this.val = val;
    }

//    @Override
    public Void call() {
        for(int i = 1 ; i <= 1000000 ; i++){
            val.value -= i;
        }
        return null;
    }

    @Override
    public void run() {
        for(int i = 1 ; i <= 1000000 ; i++){
            val.value -= i;
        }
    }
}
