package org.example.OOPSLabSession.MileStone_B;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.OOPSLabSession.MileStone_B.LibraryManagementSystem.*;

/**
 * Test Harness class to verify Milestone A functionality.
 * Tests constructors, encapsulation, polymorphism, abstract class implementation, and static/final concepts.
 */
public class Client {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();

        Book textBook = new TextBook("ISBN-001", "Data Structures", "Mark Allen", "Computer Science", BookType.TEXTBOOK, 3);
        Book novelBook = new NovelBook("ISBN-002", "The Alchemist", "Paulo Coelho", "Fiction", BookType.NOVELBOOK);
        User librarian = new Librarian("Alice", "alice@library.com", "EMP-1001");
        User member = new Member("Bob", "bob@email.com", 0);

        libraryManagementSystem.addBook(textBook);
        libraryManagementSystem.addBook(novelBook);
        libraryManagementSystem.addUser(librarian);
        libraryManagementSystem.addUser(member);

        for(Book book : bookInventory){
            book.displayBookDetails();
        }

        for(User user : registeredUsers){
            user.displayDashboard();
        }

        if(textBook.lend(member)){
            System.out.println("Member got the textbook with title : " + textBook.getTitle());
        }
        if(!textBook.lend(member)){
            System.out.println(member.toString() + " didn't get the book due to unavailability");
        }

        textBook.returnBook(member);



    }
}
