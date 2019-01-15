package com.parkeersim;

import javax.swing.*;

public class GuiView extends JPanel {
    private SimulatorView simulatorview;

    public GuiView(SimulatorView simview){
        this.simulatorview = simview;

        System.out.println("test");
        JButton start = new JButton("start");
        start.addActionListener( (e) -> simulatorview.setPause(false));
        add(start);
        JButton stop = new JButton("stop");
        stop.addActionListener( (e) -> simulatorview.setPause(true));
        add(stop);
    }

    public void drawGUI(){


        //int time = simulator.getTime();
        //String timeString = Integer.toString(time);
        //JLabel timeLabel = new JLabel(timeString);
        //add(timeLabel);
    }
}
