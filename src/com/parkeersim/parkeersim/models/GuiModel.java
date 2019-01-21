package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

public class GuiModel extends BaseModel {
    private boolean isPaused;
    private SimulatorModel simmodel;
    private InfoModel infomodel;

    public GuiModel(SimulatorModel simmodel, InfoModel infomodel){
        this.simmodel = simmodel;
        this.infomodel = infomodel;
    }

    public void pauseSim(){
        if(isPaused){
            isPaused = false;
            simmodel.setPause(false);
        }
        else if (!isPaused){
            isPaused = true;
            simmodel.setPause(true);
        }
        notifyView();
    }

    public void showInfo(){
        //new thread
        //update infoview
        infomodel.notifyView();
    }
}
