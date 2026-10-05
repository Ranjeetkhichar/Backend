package org.example.InventoryManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clothing extends Item{
    private String size;

    public Clothing(String size){
        super();
        this.size = size;
    }

}
