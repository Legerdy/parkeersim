package com.parkeersim.parkeersim.models;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    /**
     *
     *
     * @param car
     * @return
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     *
     * @return
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     *
     * @return
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
