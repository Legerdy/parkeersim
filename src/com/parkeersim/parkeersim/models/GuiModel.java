package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

public class GuiModel extends BaseModel {
    private boolean isPaused;
    private SimulatorModel model;

    public GuiModel(SimulatorModel model){
        this.model = model;
    }

    public void pauseSim(){
        if(isPaused){
            isPaused = false;
            model.setPause(false);
        }
        else if (!isPaused){
            isPaused = true;
            model.setPause(true);
        }
        notifyView();
    }
}
