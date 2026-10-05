package org.example.InventoryManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Electronics extends Item{
    private Integer warranty;

    public Electronics(Integer warranty){
        super();
        this.warranty = warranty;
    }

}
