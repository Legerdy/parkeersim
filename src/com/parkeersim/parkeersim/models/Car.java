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
    private int queueTime = 0;
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

    public void setMaxWaitTime(int time){
        this.maxWaitTime = time;
    }

    public int getWaitTime(){
        return this.maxWaitTime;
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

    public void setTypeId(int id){
        this.typeid = id;
    }

    public int getTypeId(){
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

    public void tickQueueTime(){
        queueTime++;
    }

    public int getQueueTime(){
        return queueTime;
    }
    public void setMaxWaitTime(int time){
        this.maxWaitTime = time;
    }

    public int getMaxWaitTime(){
        return this.maxWaitTime;
    }


    public abstract Color getColor();
}