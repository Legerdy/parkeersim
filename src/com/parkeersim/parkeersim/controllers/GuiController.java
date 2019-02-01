package com.parkeersim.parkeersim.controllers;

import com.parkeersim.mvc.BaseController;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.models.GuiModel;

public class GuiController extends BaseController {

    /**
     * Constructor of the class GuiController
     *
     * @param model
     */
    public GuiController(GuiModel model) {
        super(model);
    }

    /**
     * this method is a event handler, it responds to different event_id's
     * when a event is successfully done, it returns a true boolean,
     * if not, it returns a false boolean
     *
     * @param view
     * @param event_id
     * @return boolean
     */
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
