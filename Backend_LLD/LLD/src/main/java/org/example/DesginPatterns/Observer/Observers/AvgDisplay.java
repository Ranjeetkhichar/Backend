package org.example.DesginPatterns.Observer.Observers;

import org.example.DesginPatterns.Observer.Observer;

public class AvgDisplay implements Observer {
    Integer maxTemp = 0;

    @Override
    public void notify(Integer temp, Integer AQI, float precipitaion) {
        this.maxTemp  += temp;
        System.out.println("Avg Display Notified!");
        display();
    }

    void display() {
        System.out.println("Maximum temp : " + maxTemp);
    }
}
