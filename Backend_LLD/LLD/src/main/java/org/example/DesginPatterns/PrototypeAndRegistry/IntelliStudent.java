package org.example.DesginPatterns.PrototypeAndRegistry;

public class IntelliStudent extends Student {
    private int iq;

    public IntelliStudent(String name, int age, float score, int iq) {
        super(name, age, score);
        this.iq = iq;
    }

    public IntelliStudent(IntelliStudent intelliStudent) {
        super(intelliStudent);
        this.iq = intelliStudent.iq;
    }

    @Override
    public IntelliStudent clone() {
        return new IntelliStudent(this);
    }
}
