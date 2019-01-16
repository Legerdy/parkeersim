package com.parkeersim.parkeersim.views;

import com.parkeersim.Car;
import com.parkeersim.Location;
import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.models.GarageModel;

import javax.swing.*;
import java.awt.*;

public class GarageView extends BaseView{
    private Dimension size;
    private Image carParkImage;

    public GarageView(){
        size = new Dimension(500, 500);
        carParkImage = createImage(size.width, size.height);
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(900, 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */

    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Draws parking spots depending on the amount of floors, rows and places
     */
    @Override
    public void update(BaseModel model){
        updateView(model);
    }

    public void updateView(BaseModel model) {
        GarageModel garagemodel = (GarageModel) model;
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < garagemodel.getNumberOfFloors(); floor++) {
            for(int row = 0; row < garagemodel.getNumberOfRows(); row++) {
                for(int place = 0; place < garagemodel.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = garagemodel.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Draws a parking spot
     * @param color Color of the parking spot
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1);
    }
}
