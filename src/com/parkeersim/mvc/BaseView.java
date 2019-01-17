package com.parkeersim.mvc;

import javax.swing.*;

public abstract class BaseView extends JPanel {
    private BaseController controller;

    /**
     * Assign the view to a controller
     * @param controller
     */
    public void setController(BaseController controller){
        if(this.controller != null){
            this.controller = controller;
        } else {
            //throw exception the view already has a controller
        }
    }

    /**
     * Notify the views controller
     * @param event_id
     */
    public void notifyController(int event_id){
        if (this.controller != null){
            //notify
        } else {
            //throw exception the view has no controller
        }
    }

    /**
     * The model uses this method to notify the view
     * @param model
     */
    public void notify(BaseModel model){
        update(model);
    }

    /**
     * View classes can override this
     * @param model
     */
    public abstract void update(BaseModel model);

}
