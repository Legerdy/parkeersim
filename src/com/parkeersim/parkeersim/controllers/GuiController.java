package com.parkeersim.parkeersim.controllers;

import com.parkeersim.mvc.BaseController;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.models.GuiModel;

public class GuiController extends BaseController {

    public GuiController(GuiModel model) {
        super(model);
    }

    @Override
    public boolean event(BaseView view, int event_id){
        GuiModel guimodel = (GuiModel)model;
        if (event_id==1){
           guimodel.pauseSim();
           return true;
        }
        if(event_id==2){
            guimodel.showInfo();
            return true;
        }
        if(event_id==3){
            guimodel.addTickPause();
        }
        if(event_id==4){
            guimodel.subtractTickPause();
        }
        return false;
    }
}
