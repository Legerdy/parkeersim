package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.parkeersim.views.InfoView;
import com.parkeersim.parkeersim.views.SimulatorView;

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
                    view.setVisible(true);

                    String minute = "Minuut: " + model.getMinute();
                    String hour = "Hour: " + model.getHour();
                    String day = "Day: " + model.getDay();

                    view.minute(minute);
                    view.hour(hour);
                    view.day(day);
                } else {
                    view.setVisible(false);
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
