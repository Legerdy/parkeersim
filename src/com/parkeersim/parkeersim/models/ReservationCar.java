package com.parkeersim.parkeersim.models;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends Car{

    private static final Color COLOR=Color.green;

    public ReservationCar(){
        Random random = new Random();
        int stayMinutes = (int) (20 + random.nextFloat() * 2 * 60);
        int waitTime = (int)(5 + (random.nextFloat() * 60));
        this.setWaitTime(waitTime);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setTypeId(2);
    }
    public Color getColor() {return COLOR;}
}
