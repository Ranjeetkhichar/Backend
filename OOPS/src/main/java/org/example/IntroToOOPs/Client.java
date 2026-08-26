/*
 * Decompiled with CFR 0.152.
 */
package org.example.IntroToOOPs;

import org.example.IntroToOOPs.Student;

public class Client {
    public static void main(String[] args) {
        Student student = new Student();
        student.name = "Pradeep";
        student.setAge(20);
        String name = "Ranjeet";
        student.display();
        student.sayHello(name);
    }
}
