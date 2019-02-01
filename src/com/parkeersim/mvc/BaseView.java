/**
 * BaseView of the project
 *
 * @author Joost Blaauwwiekel, Daan Alssema, Dylan hasperhoven, Joris Rijs
 * @version 4.19
 */
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
            throw new IllegalStateException("The view already has a controller");
        } else {
            this.controller = controller;
        }
    }

    /**
     * Notify the views controller
     * @param event_id
     */
    public void notifyController(int event_id){
        if (this.controller != null){
            this.controller.notify(this, event_id);
        } else {
            throw new IllegalStateException("The view has no controller");
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
