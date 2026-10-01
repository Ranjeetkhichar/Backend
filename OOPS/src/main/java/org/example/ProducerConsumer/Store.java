package org.example.ProducerConsumer;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private int maxItems;
    private List<Object> items;

    public Store(int count) {
        this.maxItems = count;
        this.items = new ArrayList<>();
    }

    public List<Object> getItems() {
        return items;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void addItem(Object item) {
        System.out.println("Producer produced item having current sie " + items.size());
        items.add(item);
    }

    public void removeItem() {
        System.out.println("Consumer consumed item having current size " + items.size());
        items.remove(items.size() - 1);
    }

}
