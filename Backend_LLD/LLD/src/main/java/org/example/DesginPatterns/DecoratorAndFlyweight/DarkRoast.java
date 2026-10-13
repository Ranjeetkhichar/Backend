package org.example.DesginPatterns.DecoratorAndFlyweight;

public class DarkRoast implements Beverage {
    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public String getDiscription() {
        return "This is a Dark Roast";
    }
}
