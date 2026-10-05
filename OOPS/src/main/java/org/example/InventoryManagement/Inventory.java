package org.example.InventoryManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Inventory <T extends Item>{
    private HashMap<String, T> inventories;

    public Inventory(){
        this.inventories = new HashMap<>();
    }

    public void addItem(T item) throws Exception {
        if(item.getQuantity() < 0){
            throw new Exception("InvalidQuantityException");
        }

        if(inventories.containsKey(item.getId())){
            throw new Exception("DuplicateItemException");
        }
        inventories.put(String.valueOf(item.getId()), item);
    }

    public void removeItem(T item) throws Exception {
        if(inventories.containsKey(item.getId())){
            inventories.remove(String.valueOf(item.getId()));
        }
    }

    public T getItem(String id){
        return inventories.get(id);
    }

    public List<T> getInventories() {
        return new ArrayList<>(inventories.values());
    }

    public List<T> filterByPrice(double minPrice, double maxPrice){
        List<T> items = new ArrayList<>();
        for(T item : inventories.values()){
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                items.add(item);
            }
        }
        return new ArrayList<>(items);
    }

    public List<T> filterByAvailability(){
        List<T> items = new ArrayList<>();
        for(T item : inventories.values()){
            if(item.getQuantity() > 0){
                items.add(item);
            }
        }
        return new ArrayList<>(items);
    }

    public List<T> sortItems(Comparator<T> comparator){
        List<T> items = new ArrayList<>(inventories.values());
        items.sort(comparator);
        return items;
    }

}
