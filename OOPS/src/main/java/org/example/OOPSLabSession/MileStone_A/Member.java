package org.example.OOPSLabSession.MileStone_A;

/**
 * Concrete subclass representing a library Member.
 * Demonstrates inheritance, method overriding, constants, and polymorphism.
 */
public class Member extends User {

    private int borrowedBooksCount;
    private final int MAX_BORROW_LIMIT = 5;

    public Member() {
        super();
        this.borrowedBooksCount = 0;
    }

    public Member(String name, String contactInfo) {
        super(name, contactInfo);
        this.borrowedBooksCount = 0;
    }

    public Member(String name, String contactInfo, int borrowedBooksCount) {
        super(name, contactInfo);
        this.borrowedBooksCount = borrowedBooksCount;
    }

    public Member(Member other) {
        super(other);
        if (other != null) {
            this.borrowedBooksCount = other.borrowedBooksCount;
        }
    }

    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }

    public void setBorrowedBooksCount(int borrowedBooksCount) {
        this.borrowedBooksCount = borrowedBooksCount;
    }

    public int getMaxBorrowLimit() {
        return MAX_BORROW_LIMIT;
    }

    @Override
    public void displayDashboard() {
        System.out.println("Member Dashboard [User ID: " + getUserId() + ", Name: " + getName() + "] - Books Borrowed: " + borrowedBooksCount + "/" + MAX_BORROW_LIMIT);
    }

    @Override
    public boolean canBorrowBooks() {
        return borrowedBooksCount < MAX_BORROW_LIMIT;
    }
}
