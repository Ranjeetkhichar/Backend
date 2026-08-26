/*
 * Decompiled with CFR 0.152.
 */
package org.example.Inheritance;

public class User {
    private String name;
    protected int age;
    private String password;

    public User() {
        System.out.println("User's constructor");
    }

    public void login() {
        System.out.println("User's login method");
    }

    public static void display() {
        System.out.println("Student display of parent class");
    }
}
