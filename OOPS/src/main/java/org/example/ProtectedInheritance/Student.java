/*
 * Decompiled with CFR 0.152.
 */
package org.example.ProtectedInheritance;

import org.example.Inheritance.User;

public class Student
extends User {
    Student() {
        System.out.println("Student constructor");
    }

    void doSomething() {
        this.age = 20;
    }

    void setAge(int age) {
        this.age = age;
    }

    int getAge() {
        return this.age;
    }
}
