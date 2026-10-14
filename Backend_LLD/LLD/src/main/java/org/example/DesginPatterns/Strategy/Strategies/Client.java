package org.example.DesginPatterns.Strategy.Strategies;

import org.example.DesginPatterns.Strategy.GoogleMap;

public class Client {
    public static void main(String[] args) {
        GoogleMap map = new GoogleMap();
        map.findPath("car", "Mumbai", "Pune");
    }
}
