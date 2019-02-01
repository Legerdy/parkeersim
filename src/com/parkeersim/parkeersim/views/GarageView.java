package com.parkeersim.parkeersim.views;

import com.parkeersim.parkeersim.models.Car;
import com.parkeersim.parkeersim.models.Location;
import com.parkeersim.mvc.BaseModel;
import com.parkeersim.mvc.BaseView;
import com.parkeersim.parkeersim.models.GarageModel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GarageView extends BaseView{
    private Dimension size;
    private Image carParkImage;

    public GarageView(){
        size = new Dimension(500, 500);
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
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
     * Overrides the BaseView update method
     * @param model
     */
    @Override
    public void update(BaseModel model){
        updateView(model);
    }

    /**
     * Draws parking spots depending on the amount of floors, rows and places
     */
    public void updateView(BaseModel model) {
        GarageModel garageModel = (GarageModel) model;
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        int passPlaces = garageModel.getPassPlaces();
        Map<Integer, Location> locations = new HashMap<>();

        Integer i = new Integer(0);
        for (int floor = 0; floor < garageModel.getNumberOfFloors(); floor++) {
            for (int row = 0; row < garageModel.getNumberOfRows(); row++) {
                for (int place = 0; place < garageModel.getNumberOfPlaces(); place++) {
                    i++;
                    Location location = new Location(floor, row, place);
                    locations.put(i, location);
                }
            }
        }

        for(Map.Entry<Integer, Location> location : locations.entrySet()){
            Car car = garageModel.getCarAt(location.getValue());
            if(location.getKey()<=passPlaces && car==null){
                //parking pass place, no car
                Color color = Color.decode("#8c8c8c");
                drawPlace(graphics, location.getValue(), color);
            } else if(car!=null && car.getTypeId() == 2 && car.getStayTime() - car.getMinutesLeft() < 16){
                //reservationcar, that hasnt arrived yet
                Color color = Color.black;
                drawPlace(graphics, location.getValue(), color);
            } else {
                //others
                Color color = car == null ? Color.decode("#c6c6c6") : car.getColor();
                drawPlace(graphics, location.getValue(), color);
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
