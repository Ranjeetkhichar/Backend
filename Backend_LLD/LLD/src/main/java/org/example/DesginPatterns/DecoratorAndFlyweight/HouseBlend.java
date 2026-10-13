package org.example.DesginPatterns.DecoratorAndFlyweight;

public class HouseBlend implements Beverage {
    @Override
    public int getCost() {
        return 80;
    }
    @Override
    public String getDiscription() {
        return "This is a Dark Roast";
    }
}
