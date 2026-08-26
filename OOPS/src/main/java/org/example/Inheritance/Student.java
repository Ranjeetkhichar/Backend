/*
 * Decompiled with CFR 0.152.
 */
package org.example.Inheritance;

import org.example.Inheritance.User;

public class Student
extends User {
    float score;

    Student() {
        this.age = 18;
        System.out.println("Student constructor");
    }

    public void rateClass() {
        System.out.println("Student rating");
    }

    public static void display() {
        System.out.println("Student display of child class");
    }

    public static void display(int id) {
        System.out.println("Student display with id " + id);
    }
}
