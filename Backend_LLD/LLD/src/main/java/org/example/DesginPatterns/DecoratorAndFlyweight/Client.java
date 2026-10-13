package org.example.DesginPatterns.DecoratorAndFlyweight;

public class Client {
    public static void main(String[] args) {
        Beverage b = new DarkRoast();
        b = new Milk(b);
        b = new Milk(b); // MilkObj -> MilkObj -> DR
        b = new Cream(b);// Cream -> MilkObj -> MilkObj -> DR

        System.out.println(b.getDiscription() +": " + b.getCost());

        Beverage beverage = new HouseBlend();
        beverage = new Milk(beverage);
    }
}
