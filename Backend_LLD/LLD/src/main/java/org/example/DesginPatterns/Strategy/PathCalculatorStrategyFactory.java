package org.example.DesginPatterns.Strategy;

import org.example.DesginPatterns.Strategy.Strategies.*;

public class PathCalculatorStrategyFactory {
    public static PathCalculatorStrategy getStrategy(String mode){
        if(mode.equalsIgnoreCase("car")){
            return new CarPathCalculator();
        }
        else if(mode.equalsIgnoreCase("walk")){
            return new WalkPathCalculator();
        }
        else if(mode.equalsIgnoreCase("train")){
            return new TrainPathCalculator();
        }
        else if(mode.equalsIgnoreCase("bike")){
            return new BikePathCalculator();
        }
        throw new RuntimeException("Invalid Mode");
    }
}
