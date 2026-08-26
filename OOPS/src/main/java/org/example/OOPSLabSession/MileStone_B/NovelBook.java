package org.example.OOPSLabSession.MileStone_B;


public class NovelBook extends Book {
    private String genre;

    public NovelBook(String isbn, String title, String author, String genre, BookType type) {
        super(isbn, title, author, type);
        this.genre = genre;
    }

    @Override
    public void displayBookDetails() {
        System.out.println("NovelBook [ISBN: " + getIsbn() +
                ", Title: " + getTitle() +
                ", Author: " + getAuthor() +
                ", Genre: " + genre + "]");
    }
}

