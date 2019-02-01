package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

public class GarageModel extends BaseModel {
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int numberOfOpenParkingPassSpots;
    private Car[][][] cars;

    private int numberOfAdHocCars = 0;
    private int numberOfParkingPassCars = 0;
    private int numberOfReservationCars = 0;

    /**
     * creates the garage model
     *
     * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
     */
    public GarageModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = (numberOfFloors * numberOfRows * numberOfPlaces) - numberOfPlaces;
        this.numberOfOpenParkingPassSpots = numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    /**
     * method that returns the number of floors
     *
     * @return number of floors
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * method that returns the number of rows
     *
     * @return number of rows
     */
    public int getNumberOfRows()  {
        return numberOfRows;
    }

    /**
     * method that returns the number of places
     *
     * @return number of places
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * method that returns the number of open spots
     *
     * @return number of open spots
     */
    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    /**
     * method that returns the number of open parking pass spots
     *
     * @return number of open parking pass spots
     */
    public int getNumberOfOpenParkingPassSpots(){
        return numberOfOpenParkingPassSpots;
    }

    /**
     * method that returns if there is a car at a specific location
     *
     * @param location
     * @return cars
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * method that sets a car at a specific location
     *
     * @param location
     * @param car
     * @return boolean
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            int floor = location.getFloor();
            int row = location.getRow();
            int place = location.getPlace();
            cars[floor][row][place] = car;
            car.setLocation(location);
            if(floor == 0 && row == 0){
                numberOfOpenParkingPassSpots--;
            } else {
                numberOfOpenSpots--;
            }
            switch(car.getTypeId()){
                case 0:
                    numberOfAdHocCars++;
                    break;
                case 1:
                    numberOfParkingPassCars++;
                    break;
                case 2:
                    numberOfReservationCars++;
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * method that removes a car from a specific location
     *
     * @param location
     * @return car
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        if(location.getFloor() == 0 && location.getRow() == 0){
            numberOfOpenParkingPassSpots++;
        } else {
            numberOfOpenSpots++;
        }
        switch(car.getTypeId()){
            case 0:
                numberOfAdHocCars--;
                break;
            case 1:
                numberOfParkingPassCars--;
                break;
            case 2:
                numberOfReservationCars--;
                break;
        }
        return car;
    }

    /**
     * method that returns the first free parking pass location in the garage
     *
     * @return location
     */
    public Location getFirstFreeParkingPassLocation() {
        for(int place = 0; place < getNumberOfPlaces(); place++){
            Location location = new Location(0, 0, place);
            if (getCarAt(location) == null) {
                return location;
            }
        }
        return null;
    }

    /**
     * method that returns the first free location in the garage
     *
     * @return location
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    if(floor == 0 && row != 0){
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    } else if (floor != 0){
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * method that returns the first leaving car in the garage
     *
     * @return car
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * method that returns the number of adhoccars
     *
     * @return number of ad hoc cars
     */
    public int getNumberOfAdHocCars(){
        return numberOfAdHocCars;
    }

    /**
     * method that returns the number of parking pass cars
     *
     * @return number of parking pass cars
     */
    public int getNumberOfParkingPassCars(){
        return numberOfParkingPassCars;
    }

    /**
     * method that returns the number of reservation cars
     *
     * @return number of reservation cars
     */
    public int getNumberOfReservationCars(){
        return numberOfReservationCars;
    }

    /**
     * the tick method calls different methods that keeps the garage running
     *
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * method that updates the view of the garage
     *
     */
    public void updateView(){
        notifyView();
    }

    /**
     * method that checks if a location is valid
     *
     * @param location
     * @return boolean
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
