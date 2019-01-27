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

        //This makes the legend
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.PAGE_AXIS));
        JLabel legend = new JLabel("   Legenda:");

        legend.setFont(new Font("Serif", Font.BOLD, 14));
        JLabel adhocCar = new JLabel("<html><font color='red'>■</font> = Ad-Hoc Car</html>");
        JLabel parkingpassCar = new JLabel("<html><font color='blue'>■</font> = Parking Pass Car</html>");
        JLabel reservationCar = new JLabel("<html><font color='green'>■</font> = Reservation Car</html>");
        JLabel parkingSpace = new JLabel("<html><font color='white'>■</font> = Free Parking Space</html>");
        JLabel passSpace = new JLabel("<html><font color='#00FFFF'>■</font> = Free Parking Pass Space</html>");
        legendPanel.add(legend);
        legendPanel.add(adhocCar);
        legendPanel.add(parkingpassCar);
        legendPanel.add(reservationCar);
        legendPanel.add(parkingSpace);
        legendPanel.add(passSpace);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel contentPanel = new JPanel();
        contentPanel.add(legendPanel);
        contentPanel.add(garageview);

        add(infoview);
        add(guiview, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.PAGE_END);

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
