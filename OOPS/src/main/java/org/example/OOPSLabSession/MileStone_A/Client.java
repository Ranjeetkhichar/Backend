package org.example.OOPSLabSession.MileStone_A;

/**
 * Test Harness class to verify Milestone A functionality.
 * Tests constructors, encapsulation, polymorphism, abstract class implementation, and static/final concepts.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("=== OOPS Lab Session - Milestone A Demonstration ===\n");

        // Task 6.1: Check initial user count
        System.out.println("Initial Total Users: " + User.getTotalUsers());

        // Task 2.1 & 4.4: Test Member Constructors (Default, Parameterized, Copy)
        System.out.println("\n--- Testing Member Constructors ---");
        Member member1 = new Member();
        member1.setName("Alice");
        member1.setContactInfo("alice@example.com");

        Member member2 = new Member("Bob", "bob@example.com", 3);
        Member member3 = new Member(member2); // Copy constructor

        System.out.println("Member 1 - ID: " + member1.getUserId() + ", Name: " + member1.getName());
        System.out.println("Member 2 - ID: " + member2.getUserId() + ", Name: " + member2.getName());
        System.out.println("Member 3 (Copied from Bob) - ID: " + member3.getUserId() + ", Name: " + member3.getName());

        // Task 2.1 & 5.4: Test Librarian Constructors
        System.out.println("\n--- Testing Librarian Constructors ---");
        Librarian librarian1 = new Librarian("Charlie", "charlie@example.com", "EMP-1001");
        Librarian librarian2 = new Librarian(librarian1); // Copy constructor

        System.out.println("Librarian 1 - ID: " + librarian1.getUserId() + ", Name: " + librarian1.getName() + ", Emp #: " + librarian1.getEmployeeNumber());
        System.out.println("Librarian 2 (Copied) - ID: " + librarian2.getUserId() + ", Name: " + librarian2.getName());

        // Task 6.1 & 6.3: Verify Total Users Static Counter
        System.out.println("\n--- Testing Static Counter ---");
        System.out.println("Total Users Created So Far: " + User.getTotalUsers());

        // Task 3.2 & 4.3 & 5.3: Demonstration of Polymorphism
        System.out.println("\n--- Demonstration of Polymorphism ---");
        User[] users = new User[]{member1, member2, member3, librarian1, librarian2};

        for (User user : users) {
            // Polymorphic method call
            user.displayDashboard();
            System.out.println("   -> Can Borrow Books? " + user.canBorrowBooks());
        }

        // Testing member borrow limits
        System.out.println("\n--- Testing Borrow Limits ---");
        Member maxedMember = new Member("David", "david@example.com", 5);
        maxedMember.displayDashboard();
        System.out.println("   -> Can Borrow Books (5/5 Limit)? " + maxedMember.canBorrowBooks());

        System.out.println("\nFinal Total Users Count: " + User.getTotalUsers());

//        User user = new User();
    }
}
