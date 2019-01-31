package com.parkeersim.parkeersim;

        import com.parkeersim.parkeersim.views.SimulatorView;

        import javax.swing.*;

public class SimulatorMain {
    /**
     * main method of this project
     *
     * @param args
     */
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Parking Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Make simulatorview and add to the frame
        SimulatorView view = new SimulatorView();

        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
        //garageView needs to be notified after it has been rendered, else getGraphics() will always return null
        view.notifyGarageView();
    }
}
