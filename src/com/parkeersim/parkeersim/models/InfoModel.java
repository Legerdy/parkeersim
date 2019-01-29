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

                    double balanceRound = Math.round(model.getBalance() * 100);

                    //Infoview
                    String minute = "Minute: " + model.getMinute();
                    String hour = "Hour: " + model.getHour();
                    String day = "Day: " + model.getDay();
                    String week = "Week: " + model.getWeek();
                    String balance = "Balance: €" + balanceRound/100;
                    String parkingPassSpots = "Free parking pass spots : " + model.getOpenParkingPassSpots();
                    String parkingSpots = "Free normal parking spots : " + model.getOpenSpots();
                    String simulationSpeed = "Simulation speed : " + model.getTickPause();

                    view.minute(minute);
                    view.hour(hour);
                    view.day(day);
                    view.week(week);
                    view.balance(balance);
                    view.parkingPassSpots(parkingPassSpots);
                    view.parkingSpots(parkingSpots);
                    view.simulationSpeed(simulationSpeed);

                    //Infoview graph
                    float redangle = ((float)model.getNumberOfAdHocCars()) / model.getAllSpots() * 360;
                    float blueangle = ((float)model.getNumberOfParkingPassCars()) / model.getAllSpots() * 360;
                    float greenangle = ((float)model.getNumberOfReservationCars()) / model.getAllSpots() * 360;

                    String adhocNumber = "<html><font color='red'>" + model.getNumberOfAdHocCars() + "</font></html>";
                    String passNumber = "<html><font color='blue'>"+ model.getNumberOfParkingPassCars() + "</font></html>";
                    String reservationNumber = "<html><font color='#00ff00'>" + model.getNumberOfReservationCars() + "</font></html>";
                    String freeNumber = "<html><font color='#474747'>" + model.getTotalNumberOfOpenParkingSpaces() + "</font></html>";

                    InfoView.Graph graph = view.getGraph();
                    graph.setRedAngle((int)redangle);
                    graph.setBlueAngle((int)blueangle);
                    graph.setGreenAngle((int)greenangle);
                    graph.setAdHocNumber(adhocNumber);
                    graph.setPassNumber(passNumber);
                    graph.setReservationNumber(reservationNumber);
                    graph.setFreeNumber(freeNumber);
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
