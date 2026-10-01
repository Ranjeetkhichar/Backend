package org.example.Generics;

import lombok.Getter;

public class Animal {
    @Getter
    public String name;

    public void makeNoise() {
        System.out.println("------hurrrrrr-------");
    }
}
