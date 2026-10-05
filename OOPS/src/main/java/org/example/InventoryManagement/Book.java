package org.example.InventoryManagement;

public class Book extends Item{

    private String author;


    public Book(int id, String name, int price, int quantity, String author) {
        super(name, price, quantity);
        this.author = author;
    }

    public Book(){
        super();
    }


}
