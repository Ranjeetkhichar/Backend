package org.example.InventoryManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Comparable<Item> {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity) {
        this.id = generateUniqueId();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(){

    }

    @Override
    public int compareTo(Item o) {
        return this.price - o.price;
    }

    private int generateUniqueId() {
        return 0;
    }
}
