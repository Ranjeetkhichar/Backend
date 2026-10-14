package org.example.DesginPatterns.Observer.Observers;

import org.example.DesginPatterns.Observer.Observer;

public class MaximumDisplay implements Observer {

    Integer maxTemp = 0;

    @Override
    public void notify(Integer temp, Integer AQI, float precipitaion) {
        this.maxTemp  =Math.max(temp, maxTemp);
        System.out.println("Maximum Display Notified!");
        display();
    }

    void display() {
        System.out.println("Maximum temp : " + maxTemp);
    }
}
