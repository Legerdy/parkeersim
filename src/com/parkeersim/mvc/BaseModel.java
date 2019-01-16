package com.parkeersim.mvc;

import java.util.ArrayList;

public abstract class BaseModel {
    private ArrayList<BaseView> views = new ArrayList<>();

    /**
     * Adds views to the model
     * @param view
     */
    public void addView(BaseView view){
        views.add(view);
    }

    /**
     * Notifies all of the models' views
     */
    public void notifyView(){
        for(BaseView view : views){
            view.notify(this);
        }
    }
}
