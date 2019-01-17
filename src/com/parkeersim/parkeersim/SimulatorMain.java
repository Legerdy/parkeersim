package com.parkeersim.parkeersim;

import com.parkeersim.parkeersim.views.SimulatorView;

import javax.swing.*;
import java.awt.*;

public class SimulatorMain {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Parking Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //maak benodigde controllers models en views aan
        SimulatorView view = new SimulatorView();

        //setcontroller voor views
        //addview voor models

        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
        //garageView needs to be notified after it has been rendered, else getGraphics() will always return null
        view.notifyGarageView();
    }
}
