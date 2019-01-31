package com.parkeersim.parkeersim.models;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private int stayTime;
    private int maxWaitTime;
    private boolean isPaying;
    private boolean hasToPay;
    private int typeid;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    /**
     * method that returns the location of the car
     *
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * method that sets the location of a car
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * method that returns the amount of minutes left
     *
     * @return minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * method that sets the amount of minutes left
     *
     * @param minutesLeft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
        this.stayTime = minutesLeft;
    }

    /**
     * method that sets the wait time
     *
     * @param time
     */
    public void setWaitTime(int time){
        this.maxWaitTime = time;
    }

    /**
     * method that returns the waittime
     *
     * @return max wait time
     */
    public int getWaitTime(){
        return this.maxWaitTime;
    }

    /**
     * method that returns the stay time
     *
     * @return stay time
     */
    public int getStayTime(){
        return stayTime;
    }

    /**
     * method that returns if a car is paying
     *
     * @return is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * method that sets if the car is paying
     *
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * set the type id of a car
     *
     * @param id
     */
    public void setTypeid(int id){
        this.typeid = id;
    }

    /**
     * method that returns the type id of a car
     *
     * @return type id
     */
    public int getTypeid(){
        return this.typeid;
    }

    /**
     * method that returns if a car has to pay
     *
     * @return has to pay
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * method that sets if a car has to pay
     *
     * @param hasToPay
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     *  method that decreases the value of minutesLeft variable by 1
     *
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     *
     * @return ...
     */
    public abstract Color getColor();
}