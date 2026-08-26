package org.example.OOPSLabSession.MileStone_B;

import java.util.UUID;

@lombok.Setter
@lombok.Getter
public abstract class Book implements Lendable{

    private String isbn;
    private String title;
    private String author;
    private boolean isavailable;
    private BookType type;

    public Book(){
        this.isavailable = true; // Default to available
    }

    // Constructor for the abstract class
    public Book(String isbn, String title, String author, BookType type) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isavailable = true; // Default to available
        this.type = type;
    }

    public Book(Book book){
        this.isbn = book.isbn;
        this.title = book.title;
        this.author = book.author;
        this.isavailable = true; // Default to available
        this.type = book.type;
    }

    @Override
    public boolean lend(User user) {
        if(isavailable && user.canBorrowBooks()){
            System.out.println(this.title + " Book is issued by " + user.getName() + "!! Now it is not available");
            isavailable = false;
            return true;
        }
        return false;
    }

    @Override
    public void returnBook(User user) {
        System.out.println(this.title + " Book returned by " + user.getName() + "!! Now it is available");
        isavailable = true;
    }

    @Override
    public boolean isAvailable() {
        return isavailable;
    }

    public abstract void displayBookDetails();
}
