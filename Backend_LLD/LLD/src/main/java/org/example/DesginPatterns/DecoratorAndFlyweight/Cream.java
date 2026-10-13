package org.example.DesginPatterns.DecoratorAndFlyweight;

public class Cream implements AddOns {
    Beverage beverage;

    public Cream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDiscription() {
        return "Full Milk whipped cream";
    }
    @Override
    public int getCost() {
        return 30 + beverage.getCost();
    }
}
