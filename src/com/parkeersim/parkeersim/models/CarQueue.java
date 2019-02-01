package com.parkeersim.parkeersim.models;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class makes a LinkedList of cars which are in the queue for the parking garage.
 *
 * @author Joost Blaauwwiekel, Daan Alssema, Dylan hasperhoven, Joris Rijs
 * @version 4.19
 */

public class CarQueue {
    private LinkedList<Car> queue = new LinkedList<>();

    /**
     * This method adds a car to the queue
     *
     * @param car
     * @return boolean
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * This method removes a car from the queue
     *
     * @return Car
     */

    public Car removeCar() {
        return queue.poll();
    }

    /**
     * This method removes a specific car from the queue, compared to the other one which removes the first car from the queue
     *
     * @param car
     */

    public void removeSpecificCar(Car car) {
        queue.remove(car);
    }

    /**
     * This method returns the amount of cars which are in a queue
     *
     * @return int
     */

    public int carsInQueue() {
        return queue.size();
    }

    /**
     * This method returns a iterator, which can be used to iterate through the carqueue list.
     *
     * @return Iterator
     */

    public Iterator iterator() {
        return queue.iterator();
    }
}