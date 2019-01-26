package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.controllers.GuiController;
import com.parkeersim.parkeersim.models.GarageModel;
import com.parkeersim.parkeersim.models.GuiModel;
import com.parkeersim.parkeersim.models.InfoModel;
import com.parkeersim.parkeersim.models.SimulatorModel;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends BaseView {
    private GarageView garageview;
    private GarageModel garagemodel;

    private GuiView guiview;
    private GuiController guicontroller;
    private GuiModel guimodel;

    private SimulatorModel simulator;

    private InfoView infoview;
    private InfoModel infomodel;

    public SimulatorView(){
        //todo: do things like setting JPanel layout and such

        garageview = new GarageView();
        garagemodel = new GarageModel(3, 4, 50);
        garagemodel.addView(garageview);

        simulator = new SimulatorModel(garagemodel);

        infoview = new InfoView();
        infomodel = new InfoModel(infoview, simulator);
        infomodel.addView(infoview);

        guimodel = new GuiModel(simulator, infomodel);
        guicontroller = new GuiController(guimodel);
        guiview = new GuiView();
        guiview.setController(guicontroller);
        guimodel.addView(guiview);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(infoview);
        add(guiview, BorderLayout.PAGE_START);
        add(garageview,  BorderLayout.PAGE_END);

        //JPanel legendPanel = new JPanel();


        setVisible(true);
    }

    public void notifyGarageView(){
        garagemodel.notifyView();
        simulator.run();
    }

    @Override
    public void update(BaseModel model){

    }
}
