package com.parkeersim.parkeersim.models;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private LinkedList<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public void removeSpecificCar(Car car){
        System.out.println("Car " + car.hashCode() + " couldn't wait any longer and left");
        queue.remove(car);
    }

    public int carsInQueue(){
        return queue.size();
    }


    public Iterator iterator(){
        return queue.iterator();
    }
}
