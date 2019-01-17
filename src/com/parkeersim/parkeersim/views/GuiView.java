package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class GuiView extends BaseView {
    public GuiView(){
        setBackground(Color.GREEN);

        JButton start = new JButton("start");
        start.addActionListener( (e) -> notifyController(1));
        add(start);
        JButton stop = new JButton("stop");
        stop.addActionListener( (e) -> notifyController(1));
        add(stop);
    }

    @Override
    public void update(BaseModel model){

    }
}
