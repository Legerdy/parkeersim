package com.parkeersim.parkeersim.models;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private int stayTime;
    private boolean isPaying;
    private boolean hasToPay;
    private int typeid;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
        this.stayTime = minutesLeft;
    }

    public int getStayTime(){
        return stayTime;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public void setTypeid(int id){
        this.typeid = id;
    }

    public int getTypeid(){
        return this.typeid;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }
    
    public abstract Color getColor();
}