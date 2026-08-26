package org.example.OOPSLabSession.MileStone_B;

public class Librarian extends User {
    private String employeeNumber;

//    public Librarian() {
//        super();
//        this.employeeNumber = "EMP-0000";
//    }

    public Librarian(String name, String contactInfo, String employeeNumber) {
        super(name, contactInfo);
        this.employeeNumber = employeeNumber;
    }

    public Librarian(Librarian other) {
        super(other);
        if (other != null) {
            this.employeeNumber = other.employeeNumber;
        }
    }


    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public void displayDashboard() {
        System.out.println("Librarian Dashboard [User ID: " + getUserId() + ", Name: " + getName() + "] - Employee Number: " + employeeNumber);
    }

    @Override
    public boolean canBorrowBooks() {
        return true;
    }

}
