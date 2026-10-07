package org.example.DesginPatterns.Builder;

public class Student {
    private String name;
    private int age;
    private String email;
    private float psp;

    public Student(StudentBuilder studentBuilder) {
//        if(studentBuilder.getEmail() == null || studentBuilder.getEmail().equals("")){
//            throw new IllegalArgumentException("Email is mendatory");
//        }
//        if(studentBuilder.getAge() <= 0){
//            throw new IllegalArgumentException("Age must be greater than 0");
//        }
//        if(studentBuilder.getName() == null || studentBuilder.getName().equals("")){
//            throw new IllegalArgumentException("Name is mendatory");
//        }
        this.name = studentBuilder.getName();
        this.age = studentBuilder.getAge();
        this.email = studentBuilder.getEmail();
        this.psp = studentBuilder.getPsp();
    }

    public static StudentBuilder getBuilder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        public String getName() {
            return name;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public int getAge() {
            return age;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public float getPsp() {
            return psp;
        }

        public StudentBuilder setPsp(float psp) {
            this.psp = psp;
            return this;
        }

        public Student build(){
            if(this.getEmail() == null || this.getEmail().equals("")){
                throw new IllegalArgumentException("Email is mendatory");
            }
            if(this.getAge() <= 0){
                throw new IllegalArgumentException("Age must be greater than 0");
            }
            if(this.getName() == null || this.getName().equals("")){
                throw new IllegalArgumentException("Name is mendatory");
            }
            return new Student(this);
        }

        private String name;
        private int age;
        private String email;
        private float psp;

        private StudentBuilder() {}
    }
}
