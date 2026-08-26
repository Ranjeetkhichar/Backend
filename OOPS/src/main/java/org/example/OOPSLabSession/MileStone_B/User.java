package org.example.OOPSLabSession.MileStone_B;

/**
 * Abstract Base class representing a User in the OOPS Lab Session system.
 * Demonstrates encapsulation, abstraction, static counters, final methods, and constructor chaining.
 */
public abstract class User {
    private static int totalUsers = 0;

    private String userId;
    private String name;
    private String contactInfo;

    public static int getTotalUsers() {
        return totalUsers;
    }
    public final String generateUniqueId() {
        return "USER-" + totalUsers;
    }

    public User() {
        totalUsers++;
        this.userId = generateUniqueId();
    }

    public User(String name, String contactInfo) {
        this();
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public User(User other) {
        this();
        if (other != null) {
            this.name = other.name;
            this.contactInfo = other.contactInfo;
        }
    }

    // Task 1.2: Getters and Setters (Encapsulation)
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Task 3.1: Abstract methods enabling polymorphism across subclasses
    public abstract void displayDashboard();
    public abstract boolean canBorrowBooks();
}
