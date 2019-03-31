package interview_01;

import java.util.ArrayList;
import java.util.List;

// 实现观察者模式

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class Weather implements Subject {
    
    private List<Observer> observers = new ArrayList<>();
    
    private double temperature;

    public void weatherChange() {
        notifyObservers();
    }
    
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

interface Observer {
    void update(double temperature);
}

class WeatherDisplay1 implements Observer {

    private double temperature;
    
    public WeatherDisplay1(Subject weather) {
        weather.registerObserver(this);
    }
    
    @Override
    public void update(double temperature) {
        this.temperature = temperature;
        display();
    }
    
    public void display() {
        System.out.println("display1***** : " + temperature);
    }
}

class WeatherDisplay2 implements Observer {

    private double temperature;
    
    public WeatherDisplay2(Subject weather) {
        weather.registerObserver(this);
    }
    
    @Override
    public void update(double temperature) {
        this.temperature = temperature;
        display();
    }
    
    public void display() {
        System.out.println("display1----- : " + temperature);
    }
}

public class ObserverPattern {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Weather weather = new Weather();
        WeatherDisplay1 display1 = new WeatherDisplay1(weather);
        WeatherDisplay2 display2 = new WeatherDisplay2(weather);
        weather.setTemperature(27);
        weather.setTemperature(20);
    }
}
