package com.parkeersim.parkeersim.models;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends Car{

    private static final Color COLOR=Color.green;

    public ReservationCar(){
        Random random = new Random();
        int stayMinutes = (int) (17+ random.nextFloat() * 4 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setTypeid(2);
    }
    public Color getColor() {return COLOR;}
}
