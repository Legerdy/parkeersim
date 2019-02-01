/**
 * BaseController of the project
 *
 * @author Joris Rijs, Daan Alssema, Dylan Hasperhoven, Joost Blaauwwiekel
 * @version 4.19
 *
 */
package com.parkeersim.mvc;

public abstract class BaseController {
    protected final BaseModel model;

    /**
     * Constructor that attaches model to the controller
     * @param model
     */
    public BaseController(BaseModel model){
        this.model = model;
    }

    /**
     * Method to get notified by view
     * @param view
     * @param event_id
     */
    public void notify(BaseView view, int event_id){
        if(!event(view, event_id)){
            //throw exception didn't handle event
        }
    }

    /**
     * Override to handle events
     * @param view
     * @param event_id
     * @return
     */
    public abstract boolean event(BaseView view, int event_id);
}
