package org.example.DesginPatterns.Observer.Observers;

import org.example.DesginPatterns.Observer.Observer;

public class ForecastDisplay implements Observer {

    @Override
    public void notify(Integer temp, Integer AQI, float precipitaion) {
        System.out.println("Forecast Display Notified!");
        display();
    }

    public void display() {
        System.out.println("Forecast data unavailable!");
    }
}
