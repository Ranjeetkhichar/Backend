package org.example.DesginPatterns.Strategy;

import org.example.DesginPatterns.Strategy.Strategies.PathCalculatorStrategy;

public class GoogleMap {
    public void findPath(String mode, String src, String dest){
        PathCalculatorStrategy pc = PathCalculatorStrategyFactory.getStrategy(mode);
        pc.calculatePath(src, dest);
    }
}
