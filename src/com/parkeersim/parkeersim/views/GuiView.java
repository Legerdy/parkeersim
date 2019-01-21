package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class GuiView extends BaseView {

    public GuiView(){
        setBackground(Color.GREEN);

        //Button that starts/stops the simulation
        JButton startstop = new JButton("stop");
        startstop.addActionListener( (e) -> {
            notifyController(1);
            if(startstop.getText() == "stop"){
                startstop.setText("start");
            } else {
                startstop.setText("stop");
            }
        });
        add(startstop);

        //button to open the statistics view
        JButton openinfo = new JButton("Show Information");
        openinfo.addActionListener( (e -> notifyController(2)));
        add(openinfo);
    }

    @Override
    public void update(BaseModel model){

    }
}
