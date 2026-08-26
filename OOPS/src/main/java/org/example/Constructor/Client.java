/*
 * Decompiled with CFR 0.152.
 */
package org.example.Constructor;

import org.example.Constructor.Exam;
import org.example.Constructor.Student;

public class Client {
    public static void main(String[] args) {
        Student s1;
        Student s2 = s1 = new Student();
        Student s3 = new Student(s1);
        Student s4 = new Student("Pradeep", 20, "B.Tech", "ECE");
        s2.name = "Sam";
        s3.haveFun();
        s4.haveFun();
        System.out.println(Student.count);
        System.out.println(Student.count);
        System.out.println(Exam.count);
        Student.printStudentCount();
        Student.something(s1);
        System.out.println("DEBUG");
    }
}
