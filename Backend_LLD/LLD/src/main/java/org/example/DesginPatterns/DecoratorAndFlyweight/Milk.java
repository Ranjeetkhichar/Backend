package org.example.DesginPatterns.DecoratorAndFlyweight;


public class Milk implements AddOns{
    Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int getCost() {
        return 20 + beverage.getCost();
    }

    @Override
    public String getDiscription() {
        return beverage.getDiscription() + " + Full milk sweetened";
    }
}
