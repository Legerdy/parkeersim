package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import java.awt.*;

public class InfoView extends BaseView {
    private JFrame frame = new JFrame();
    private JPanel infoPanel = new JPanel();
    private Graph graphPanel = new Graph();

    private JPanel timePanel = new JPanel();
    private JLabel minutelabel = new JLabel();
    private JLabel hourlabel = new JLabel();
    private JLabel daylabel = new JLabel();

    private JPanel parkingSpotsPanel = new JPanel();
    private JLabel openParkingPassSpots = new JLabel();
    private JLabel openParkingSpots = new JLabel();

    private JPanel simulationSpeedPanel = new JPanel();
    private JLabel simulationSpeed = new JLabel();



    public InfoView(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());
        frame.setSize(400,400);
        frame.setLocation(982,0);

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

        infoPanel.add(timePanel, BorderLayout.PAGE_START);
        timePanel.add(daylabel);
        timePanel.add(hourlabel);
        timePanel.add(minutelabel);
        timePanel.setBackground(Color.decode("#8c8c8c"));

        infoPanel.add(parkingSpotsPanel);
        parkingSpotsPanel.add(openParkingPassSpots);
        parkingSpotsPanel.add(openParkingSpots);
        parkingSpotsPanel.setBackground(Color.decode("#8c8c8c"));;

        infoPanel.add(simulationSpeedPanel, BorderLayout.PAGE_END);
        simulationSpeedPanel.add(simulationSpeed);
        simulationSpeedPanel.setBackground(Color.decode("#8c8c8c"));

        frame.add(infoPanel, BorderLayout.PAGE_START);
        frame.add(graphPanel);
    }

    public Graph getGraph(){
        return graphPanel;
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

    public void parkingPassSpots(String string){
        openParkingPassSpots.setText(string);
    }

    public void parkingSpots(String string){
        openParkingSpots.setText(string);
    }

    public void simulationSpeed(String string){
        simulationSpeed.setText(string);
    }

    public class Graph extends JPanel{
        private int redAngle;
        private int blueAngle;
        private int greenAngle;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.fillArc(85,50,200,200,0,360);
            g.setColor(Color.RED);
            g.fillArc(85,50,200,200,0,redAngle);
            g.setColor(Color.BLUE);
            g.fillArc(85,50,200,200,0,-blueAngle);
            g.setColor(Color.GREEN);
            g.fillArc(85,50,200,200,blueAngle,-greenAngle);
            repaint();
        }

        public void setRedAngle(int angle){
            this.redAngle = angle;
        }

        public void setBlueAngle(int angle){
            this.blueAngle = angle;
        }

        public void setGreenAngle(int angle){
            this.greenAngle = angle;
        }

        public Graph(){
            add(new JLabel("Auto's in de garage:"));
        }
    }

    @Override
    public void update(BaseModel model){

    }
}
