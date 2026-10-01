package org.example.Generics;

import lombok.*;


@Getter
@Setter
public class Pair<T, V> {
    private T first;
    private V second;

    public Pair(T x, V y){
        this.first = x;
        this.second = y;
    }

    public T something(){

        return first;
    }

    static <T> void some(T first){

    }

    static <T> void doNothing(T first, Integer second){

    }

    public static <T, V> void doSomething(T first, V second){
        System.out.println("First is : " + first.toString() + " Second is : " + second.toString());

    }

    public <S>void someNonStaticMethod(T first, S second){

    }

}
