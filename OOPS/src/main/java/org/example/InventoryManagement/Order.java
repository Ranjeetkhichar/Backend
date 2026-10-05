package org.example.InventoryManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order implements Comparable<Order> {

    private String OrderId;
    private boolean isExpress;

    public Order(String orderId, boolean isExpress) {
        this.OrderId = orderId;
        this.isExpress = isExpress;
    }


    @Override
    public int compareTo(Order o) {
        if(this.isExpress && o.isExpress) {
            return this.OrderId.compareTo(o.OrderId);
        }
        return this.isExpress ? -1 : 1;
    }

}
