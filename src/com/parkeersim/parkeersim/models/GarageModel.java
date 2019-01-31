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

    public GarageModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = (numberOfFloors * numberOfRows * numberOfPlaces) - numberOfPlaces;
        this.numberOfOpenParkingPassSpots = numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public int getNumberOfOpenParkingPassSpots(){
        return numberOfOpenParkingPassSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

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

    public Location getFirstFreeParkingPassLocation() {
        for(int place = 0; place < getNumberOfPlaces(); place++){
            Location location = new Location(0, 0, place);
            if (getCarAt(location) == null) {
                return location;
            }
        }
        return null;
    }

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

    public int getNumberOfAdHocCars(){
        return numberOfAdHocCars;
    }

    public int getNumberOfParkingPassCars(){
        return numberOfParkingPassCars;
    }

    public int getNumberOfReservationCars(){
        return numberOfReservationCars;
    }

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

    public void updateView(){
        notifyView();
    }

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
