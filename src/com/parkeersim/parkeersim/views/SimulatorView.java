package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.controllers.GuiController;
import com.parkeersim.parkeersim.models.GarageModel;
import com.parkeersim.parkeersim.models.GuiModel;
import com.parkeersim.parkeersim.models.SimulatorModel;

public class SimulatorView extends BaseView {
    private GarageView garageview;
    private GarageModel garagemodel;
    private GuiView guiview;
    private GuiController guicontroller;
    private GuiModel guimodel;
    private SimulatorModel simulator;

    public SimulatorView(){
        //do things like setting JPanel layout and such
        //new GarageView JPanel, add to this JPanel
        //new GuiView JPanel, add to this JPanel
        garageview = new GarageView();
        garagemodel = new GarageModel(3, 4, 50);
        garagemodel.addView(garageview);

        simulator = new SimulatorModel(garagemodel);

        guimodel = new GuiModel(simulator);
        guicontroller = new GuiController(guimodel);
        guiview = new GuiView();
        guiview.setController(guicontroller);
        guimodel.addView(guiview);

        add(guiview);
        add(garageview);
        setVisible(true);
    }

    public void notifyGarageView(){
        garagemodel.notifyView();
        simulator.run();
    }

    @Override
    public void update(BaseModel model){
        System.out.println("test");
        //this.repaint();
    }
}
