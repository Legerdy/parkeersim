package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class InfoView extends BaseView {
    private JLabel minutelabel = new JLabel();
    private JLabel hourlabel = new JLabel();
    private JLabel daylabel = new JLabel();

    public InfoView(){
        setBackground(Color.red);
        add(minutelabel);
        add(hourlabel);
        add(daylabel);

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
