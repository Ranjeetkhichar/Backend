package org.example.AdderSubstractorThreadSafe;

import java.util.concurrent.Callable;

public class Substractor implements Callable<Integer> {

    private Value val;
    public Substractor(Value val) {
        this.val = val;
    }

    @Override
    public Integer call() {
        for(int i = 1 ; i <= 1000000 ; i++){
            val.value -= i;
        }
        return val.value;
    }

}
