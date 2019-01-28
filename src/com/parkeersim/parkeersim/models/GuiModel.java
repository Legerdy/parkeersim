package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

public class GuiModel extends BaseModel {
    private boolean simPaused;
    private boolean infoVisible;

    private SimulatorModel simmodel;
    private InfoModel infomodel;

    public GuiModel(SimulatorModel simmodel, InfoModel infomodel){
        this.simmodel = simmodel;
        this.infomodel = infomodel;
    }

    public void pauseSim(){
        if(simPaused){
            simPaused = false;
            simmodel.setPause(false);
        }
        else if (!simPaused){
            simPaused = true;
            simmodel.setPause(true);
        }
        simmodel.notifyView();
    }

    public void addTickPause(){
        if(simmodel.getTickPause() == 1){
            simmodel.setTickPause(5);
        } else {
            simmodel.setTickPause(simmodel.getTickPause() + 5);
        }
    }

    public void subtractTickPause(){
        if(simmodel.getTickPause() > 6){
            simmodel.setTickPause(simmodel.getTickPause() - 5);
        } else {
            simmodel.setTickPause(1);
        }
    }

    public void showInfo(){
        if(infoVisible){
            infoVisible = false;
            infomodel.setVisible(false);
        }
        else if (!infoVisible){
            infoVisible = true;
            infomodel.setVisible(true);
        }
        infomodel.notifyView();
    }
}
