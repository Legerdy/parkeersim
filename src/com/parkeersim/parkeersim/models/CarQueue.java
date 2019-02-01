package com.parkeersim.parkeersim.models;
import java.util.Iterator;
import java.util.LinkedList;

public class CarQueue {
    private LinkedList<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public void removeSpecificCar(Car car){
        queue.remove(car);
    }

    public int carsInQueue(){
        return queue.size();
    }

    public Iterator iterator(){
        return queue.iterator();
    }
}
