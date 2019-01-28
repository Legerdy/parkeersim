package com.parkeersim.parkeersim.models;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setTypeid(0);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
