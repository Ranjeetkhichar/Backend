package org.example.InventoryManagement;

import java.util.PriorityQueue;
import java.util.Queue;

public class OrderProcessor {
    private Queue<Order> orders;

    public OrderProcessor() {
        orders = new PriorityQueue<>();
    }

    public void addOrder(Order order) {
//        orders.add(order);
        orders.offer(order);
    }

    public Order processOrder(Order order) {
        return orders.poll();
    }

    public Integer getSize(){
        return orders.size();
    }
}
