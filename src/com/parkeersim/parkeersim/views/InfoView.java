package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class InfoView extends BaseView {
    private JFrame frame = new JFrame();

    private JLabel minutelabel = new JLabel();
    private JLabel hourlabel = new JLabel();
    private JLabel daylabel = new JLabel();

    public InfoView(){
        frame.setLayout(new FlowLayout());
        frame.setSize(400,400);

        frame.setBackground(Color.red);
        frame.add(daylabel);
        frame.add(hourlabel);
        frame.add(minutelabel);

        //frame.setVisible(true);
    }

    public void setFrameVisible(boolean state){
        frame.setVisible(state);
    }

    public void minute(String string){
        minutelabel.setText(string);
    }

    public void hour(String string){
        hourlabel.setText(string);
    }

    public void day(String string){
        daylabel.setText(string);
    }

    @Override
    public void update(BaseModel model){

    }
}
