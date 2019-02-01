package com.parkeersim.parkeersim.models;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        int waitTime = (int)(5 + (random.nextFloat() * 60));
        this.setWaitTime(waitTime);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setTypeId(1);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
