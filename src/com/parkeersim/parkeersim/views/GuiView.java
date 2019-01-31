package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class GuiView extends BaseView {

    public GuiView(){
        setBackground(Color.DARK_GRAY);
        Dimension d = new Dimension(200, 40);
        //setPreferredSize(d);
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //setLayout(new FlowLayout());

        //Button that starts/stops the simulation
        JButton startstop = new JButton("Stop");
        startstop.addActionListener( (e) -> {
            notifyController(1);
            if(startstop.getText() == "Stop"){
                startstop.setText("Start");
            } else {
                startstop.setText("Stop");
            }
        });
        add(startstop, BorderLayout.CENTER);

        //button to open the statistics view
        JButton openinfo = new JButton("Show Information");
        openinfo.addActionListener( (e -> {
            notifyController(2);
            if(openinfo.getText() == "Show Information"){
                openinfo.setText("Close Information");
            } else {
                openinfo.setText("Show Information");
            }
        }));
        add(openinfo);

        //buttons for changing simulation speed
        JButton addspeed = new JButton("Lower Simulation Speed");
        addspeed.addActionListener( (e) -> notifyController(3));
        add(addspeed);
        JButton removespeed = new JButton("Higher Simulation Speed");
        removespeed.addActionListener( (e) -> notifyController(4));
        add(removespeed);
    }

    @Override
    public void update(BaseModel model){

    }
}
