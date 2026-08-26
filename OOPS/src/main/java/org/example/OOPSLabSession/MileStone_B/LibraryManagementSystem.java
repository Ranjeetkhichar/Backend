package org.example.OOPSLabSession.MileStone_B;

import java.util.ArrayList;
import java.util.List;

@lombok.Setter
@lombok.Getter
public class LibraryManagementSystem {

    static List<Book> bookInventory;   //store all books in library

    static List<User> registeredUsers; //all register user for lib who can issue book

    public LibraryManagementSystem() {
        bookInventory = new ArrayList<>();
        registeredUsers = new ArrayList<>();
    }

    void addBook(Book book) {
        bookInventory.add(book);
    }
    void addUser(User user) {
        registeredUsers.add(user);
    }



    static List<Book> searchBooks(String criteria, String type) {
        List<Book> books = new ArrayList<>();
        try {
            BookType bookType = BookType.valueOf(type.toUpperCase());
            for (Book book : bookInventory) {
                if (book.getType().equals(bookType) &&
                    (book.getAuthor().contains(criteria) || book.getTitle().contains(criteria))) {
                    books.add(book);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid book type: " + type + ". Valid types are: TEXTBOOK, NOVELBOOK");
        }
        return books;
    }

    static List<Book> searchBooksByType(String type) {
        List<Book> books = new ArrayList<>();
        try {
            BookType bookType = BookType.valueOf(type.toUpperCase());
            for (Book book : bookInventory) {
                if (book.getType().equals(bookType)) {
                    books.add(book);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid book type: " + type + ". Valid types are: TEXTBOOK, NOVELBOOK");
        }
        return books;
    }

    static List<Book> searchBooks(String criteria){
        List<Book> books = new ArrayList<>();
        if (criteria == null || criteria.trim().isEmpty()) {
            System.out.println("Search criteria cannot be null or empty");
            return books;
        }
        for(Book book : bookInventory){
            if(book.getAuthor().contains(criteria) || book.getTitle().contains(criteria)){
                books.add(book);
            }
        }
        return books;
    }

    public void displayAllBooks(){
        for(Book book : bookInventory){
            book.displayBookDetails();
        }
    }

    public void displayAllUsers(){
        for(User user : registeredUsers){
            user.displayDashboard();
        }
    }
//    static void main(String[] args) {
//        LibraryManagementSystem libraryManagementSystem = null;
//        Book book1 = new Book();
//        Book book2 = new Book();
//
//        libraryManagementSystem.addBook(book1);
//        libraryManagementSystem.addBook(book2);
//
//    }

}
