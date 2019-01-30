package com.parkeersim.parkeersim.views;

import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoView extends BaseView {
    private JFrame frame = new JFrame();
    private JPanel infoPanel = new JPanel();
    private Graph graphPanel = new Graph();

    private JPanel timePanel = new JPanel();
    private JLabel weeklabel = new JLabel();
    private JLabel minutelabel = new JLabel();
    private JLabel hourlabel = new JLabel();
    private JLabel daylabel = new JLabel();

    private JPanel balancePanel = new JPanel();
    private JLabel balanceLabel = new JLabel();

    private JPanel expansesPanel = new JPanel();
    private JLabel expansesLabel= new JLabel();

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
        timePanel.add(weeklabel);
        timePanel.add(daylabel);
        timePanel.add(hourlabel);
        timePanel.add(minutelabel);
        timePanel.setBackground(Color.decode("#8c8c8c"));

        infoPanel.add(balancePanel);
        balancePanel.add(balanceLabel);
        balancePanel.setBackground(Color.decode("#828282"));

        infoPanel.add(expansesPanel);
        expansesPanel.add(expansesLabel);
        balancePanel.setBackground(Color.decode("#828282"));

        infoPanel.add(parkingSpotsPanel);
        parkingSpotsPanel.add(openParkingPassSpots);
        parkingSpotsPanel.add(openParkingSpots);
        parkingSpotsPanel.setBackground(Color.decode("#8c8c8c"));

        infoPanel.add(simulationSpeedPanel, BorderLayout.PAGE_END);
        simulationSpeedPanel.add(simulationSpeed);
        simulationSpeedPanel.setBackground(Color.decode("#828282"));

        frame.add(infoPanel, BorderLayout.PAGE_START);
        frame.add(graphPanel);
    }

    public Graph getGraph(){
        return graphPanel;
    }

    public void setFrameVisible(boolean state){
        frame.setVisible(state);
    }

    public void week(String string) { weeklabel.setText(string); }

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

    public void balance(String string){
        balanceLabel.setText(string);
    }

    public void expanses(String string) {
        expansesLabel.setText(string);
    }

    public class Graph extends JPanel{
        private int redAngle;
        private int blueAngle;
        private int greenAngle;

        private JLabel numberAdHoc = new JLabel();
        private JLabel numberPass = new JLabel();
        private JLabel numberReservation = new JLabel();
        private JLabel numberFree = new JLabel();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.fillArc(170,40,200,200,0,360);
            g.setColor(Color.RED);
            g.fillArc(170,40,200,200,0,redAngle);
            g.setColor(Color.BLUE);
            g.fillArc(170,40,200,200,0,-blueAngle);
            g.setColor(Color.GREEN);
            g.fillArc(170,40,200,200,-blueAngle,-greenAngle);
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

        public void setAdHocNumber(String string){
            numberAdHoc.setText(string);
        }

        public void setPassNumber(String string){
            numberPass.setText(string);
        }

        public void setReservationNumber(String string){
            numberReservation.setText(string);
        }

        public void setFreeNumber(String string){
            numberFree.setText(string);
        }

        public Graph(){
            JPanel panel = new JPanel();

            JLabel label = new JLabel("Cars in the garage:");
            label.setFont(new Font("SansSerif", Font.BOLD, 14));

            setLayout(null);
            label.setLocation(125,0);
            label.setSize(200,25);

            panel.setLocation(0,50);
            panel.setSize(150,150);

            add(panel);
            add(label);

            JLabel AdHoc = new JLabel("Ad-Hoc cars: ");
            AdHoc.setSize(130,20);
            AdHoc.setLocation(5,57);
            numberAdHoc.setSize(40,20);
            numberAdHoc.setLocation(120,57);
            JLabel Pass = new JLabel("Parking Pass cars:");
            Pass.setSize(130,20);
            Pass.setLocation(5,74);
            numberPass.setSize(40,20);
            numberPass.setLocation(120,74);
            JLabel Reservation = new JLabel("Reservation cars:");
            Reservation.setSize(130,20);
            Reservation.setLocation(5,91);
            numberReservation.setSize(40,20);
            numberReservation.setLocation(120,91);
            JLabel Free = new JLabel("Total free spots:");
            Free.setSize(130,20);
            Free.setLocation(5,108);
            numberFree.setSize(40,20);
            numberFree.setLocation(120,108);

            panel.setLayout(null);
            panel.add(numberAdHoc);
            panel.add(numberPass);
            panel.add(numberReservation);
            panel.add(numberFree);
            panel.add(AdHoc);
            panel.add(Pass);
            panel.add(Reservation);
            panel.add(Free);
        }
    }

    @Override
    public void update(BaseModel model){

    }
}
