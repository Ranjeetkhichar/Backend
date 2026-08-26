/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package org.example.IntroToOOPs;

import lombok.Generated;

public class Student {
    public String name;
    private int age;

    public void display() {
        System.out.println("My name is " + this.name + ". I am " + this.age + " years old");
    }

    public void sayHello(String name) {
        System.out.println(this.name + " says hello to " + name);
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public int getAge() {
        return this.age;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setAge(int age) {
        this.age = age;
    }
}
