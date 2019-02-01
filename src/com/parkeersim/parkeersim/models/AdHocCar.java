package com.parkeersim.parkeersim.models;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;

    /**
     * ...
     */
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    	int waitTime = (int)(5 + (random.nextFloat() * 60));
    	this.setMaxWaitTime(waitTime);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setTypeId(0);
    }

    /**
     * method that returns the color of the car
     *
     * @return color
     */
    public Color getColor(){
    	return COLOR;
    }
}
