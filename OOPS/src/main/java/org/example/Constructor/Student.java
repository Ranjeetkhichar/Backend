/*
 * Decompiled with CFR 0.152.
 */
package org.example.Constructor;

import org.example.Constructor.Exam;

public class Student {
    public String name;
    private int age;
    protected String course;
    public String psp;
    Exam exam;
    static int count;

    public Student(String name, int age, String course, String psp) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.psp = psp;
        ++count;
        this.exam = new Exam();
    }

    public Student() {
        this.name = "Default";
        this.age = 0;
        this.course = "Default";
        this.psp = "";
        ++count;
    }

    public Student(Student student) {
        this.name = student.name;
        this.age = student.age;
        student.age = 20;
        this.course = student.course;
        this.psp = student.psp;
        this.exam = new Exam(student.exam);
        ++count;
    }

    void haveFun() {
        this.name = "Ram";
        this.age = 18;
    }

    public static void printStudentCount() {
        int x = 10;
        System.out.println("Student count = " + count);
    }

    public void doSome() {
        System.out.println(count);
        Student.printStudentCount();
    }

    public static void something(Student s) {
        s.age = 20;
        System.out.println(s.name);
        Student.printStudentCount();
    }
}
