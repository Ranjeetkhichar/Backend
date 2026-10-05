package org.example.InventoryManagement;

import java.util.Comparator;

public class ItemQuantityComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getQuantity() - o2.getQuantity();
    }
}
