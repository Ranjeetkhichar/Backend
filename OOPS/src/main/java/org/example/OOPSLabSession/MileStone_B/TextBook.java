package org.example.OOPSLabSession.MileStone_B;

public class TextBook extends Book {
    private String subject;
    private int edition;

    public TextBook(String isbn, String title, String author, String subject, BookType type, int edition) {
        super(isbn, title, author, type);
        this.subject = subject;
        this.edition = edition;
    }

    @Override
    public void displayBookDetails() {
        System.out.println("TextBook [ISBN: " + getIsbn() +
                ", Title: " + getTitle() +
                ", Author: " + getAuthor() +
                ", Subject: " + subject + "]");
    }
}
