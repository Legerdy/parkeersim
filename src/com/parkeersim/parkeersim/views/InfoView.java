package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class InfoView extends BaseView {
    private String carcount = "test";

    public InfoView(){
        setVisible(false);
    }

    @Override
    public void update(BaseModel model){
        setVisible(true);
        JLabel carcountLabel = new JLabel(carcount);
        add(carcountLabel);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 50, 50);

        int angle=(360*(100/(20)));
        g.setColor(Color.BLUE);
        g.fillArc(10, 10, 180, 180, 0, angle);
        g.setColor(Color.LIGHT_GRAY);
        g.fillArc(10, 10, 180, 180, angle, 360-angle);
    }
}
