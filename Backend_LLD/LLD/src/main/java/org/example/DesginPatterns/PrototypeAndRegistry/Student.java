package org.example.DesginPatterns.PrototypeAndRegistry;

public class Student implements Prototype<Student> {
    private String name;
    private int age;
    private float score;

    public Student(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student(Student s){
        this.name = s.name;
        this.age = s.age;
        this.score = s.score;
    }

    @Override
    public Student clone() {
        return new Student(this);
    }

}
