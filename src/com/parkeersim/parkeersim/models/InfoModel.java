package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.parkeersim.views.InfoView;

import java.math.BigDecimal;

public class InfoModel extends BaseModel {
    private boolean isVisible = false;
    private InfoView view;
    private SimulatorModel model;

    public InfoModel(InfoView view, SimulatorModel model){
        this.view = view;
        this.model = model;
        tick();
    }

    public void tick(){
        Thread thread = new Thread(() -> {
            while (true){
                if(isVisible){
                    view.setFrameVisible(true);

                    String minute = "Minute: " + model.getMinute();
                    String hour = "Hour: " + model.getHour();
                    String day = "Day: " + model.getDay();
                    String parkingPassSpots = "Free parking pass spots : " + model.getOpenParkingPassSpots();
                    String parkingSpots = "Free parking spots : " + model.getOpenSpots();
                    String simulationSpeed = "Simulation speed : " + model.getTickPause();

                    view.minute(minute);
                    view.hour(hour);
                    view.day(day);
                    view.parkingPassSpots(parkingPassSpots);
                    view.parkingSpots(parkingSpots);
                    view.simulationSpeed(simulationSpeed);

                    float redangle = ((float)model.getNumberOfAdHocCars()) / model.getAllSpots() * 360;
                    float blueangle = ((float)model.getNumberOfParkingPassCars()) / model.getAllSpots() * 360;

                    InfoView.Graph graph = view.getGraph();
                    graph.setRedAngle((int)redangle);
                    graph.setBlueAngle((int)blueangle);
                    //graph.setGreenAngle(model.getNumberOfReservationCars() / model.getAllSpots() * 360);
                } else {
                    view.setFrameVisible(false);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void setVisible(boolean state){
        isVisible = state;
    }
}
