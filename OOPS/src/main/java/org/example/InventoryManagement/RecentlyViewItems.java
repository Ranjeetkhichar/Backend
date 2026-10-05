package org.example.InventoryManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecentlyViewItems {
    private LinkedList<Item> recentlyViewedItems;
    private Integer maxItems;

    public RecentlyViewItems(Integer maxItems) {
        this.maxItems = maxItems;
        this.recentlyViewedItems = new LinkedList<>();
    }

    public void addRecentlyViewedItem(Item item){

        this.recentlyViewedItems.remove(item);

        if(this.recentlyViewedItems.size() == maxItems){
            this.recentlyViewedItems.removeLast();
        }
        this.recentlyViewedItems.addFirst(item);
    }

    public List<Item> getRecentlyViewedItems() {
        return new ArrayList<>(recentlyViewedItems);
    }

}
