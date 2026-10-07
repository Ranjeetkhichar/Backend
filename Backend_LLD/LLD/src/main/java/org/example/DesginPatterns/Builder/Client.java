package org.example.DesginPatterns.Builder;

import org.example.DesginPatterns.Builder.Student.StudentBuilder;

public class Client {
    public static void main(String[] args) {
        //Solution 1
//        StudentBuilder studentBuilder = new StudentBuilder();
//        studentBuilder.setAge(12);
//        studentBuilder.setEmail("some@some.com");
//        studentBuilder.setPsp(10);
//        studentBuilder.setName("SomeName");

        //Solution 2
        StudentBuilder studentBuilder = Student.getBuilder();
        studentBuilder.setAge(12);
        studentBuilder.setEmail("some@some.com");
        studentBuilder.setPsp(10);
        studentBuilder.setName("SomeName");

        //Solution 3
        Student.StudentBuilder studentBuilder1 = Student.getBuilder()
                .setAge(12)
                .setName("SomeName")
                .setPsp(10)
                .setEmail("some@some.com");


        //Solution 4
        Student s1 = Student.getBuilder()
                .setAge(12)
                .setName("SomeName")
                .setPsp(10)
                .setEmail("some@some.com")
                .build();

        Student student = new Student(studentBuilder);
        System.out.println("DEBUG");
    }
}
