package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

import java.util.*;

public class GarageModel extends BaseModel {
    private SimulatorModel simulator;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int numberOfOpenParkingPassSpots;
    private Car[][][] cars;

    private int numberOfAdHocCars = 0;
    private int numberOfParkingPassCars = 0;
    private int numberOfReservationCars = 0;

    private int passPlaces;

    public GarageModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

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

    public int getPassPlaces(){
        return passPlaces;
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

            Map<Integer, Location> locations = getLocations();
            for(Map.Entry<Integer, Location> location2 : locations.entrySet()) {
                if (location.getPlace() == location2.getValue().getPlace()
                    && location.getRow() == location2.getValue().getRow()
                    && location.getFloor() == location2.getValue().getFloor()) {
                    if(location2.getKey() <= passPlaces){
                        numberOfOpenParkingPassSpots--;
                    } else {
                        numberOfOpenSpots--;
                    }
                }
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

        Map<Integer, Location> locations = getLocations();
        for(Map.Entry<Integer, Location> location2 : locations.entrySet()) {
            if (location.getPlace() == location2.getValue().getPlace()
                && location.getRow() == location2.getValue().getRow()
                && location.getFloor() == location2.getValue().getFloor()) {
                if (location2.getKey() <= passPlaces){
                    numberOfOpenParkingPassSpots++;
                } else {
                    numberOfOpenSpots++;
                }
            }
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

    private Map<Integer, Location> getLocations(){
        Map<Integer, Location> locations = new HashMap<>();

        Integer i = new Integer(0);
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    i++;
                    Location location = new Location(floor, row, place);
                    locations.put(i, location);
                }
            }
        }
        return locations;
    }

    public Location getFirstFreeParkingPassLocation() {
        Map<Integer, Location> locations = getLocations();

        for(Map.Entry<Integer, Location> location : locations.entrySet()){
            if(getCarAt(location.getValue()) == null && location.getKey()<=passPlaces){
                return location.getValue();
            }
        }
        return null;
    }

    public Location getFirstFreeLocation() {
        Map<Integer, Location> locations = getLocations();
        System.out.println(numberOfOpenParkingPassSpots + " : " + numberOfOpenSpots);

        for(Map.Entry<Integer, Location> location : locations.entrySet()){
            if(getCarAt(location.getValue()) == null && location.getKey()>passPlaces){
                return location.getValue();
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

    public void setSimulator(SimulatorModel simulator){
        this.simulator = simulator;
        this.passPlaces = simulator.getPassPlaces();
        this.numberOfOpenSpots = (numberOfFloors * numberOfRows * numberOfPlaces) - passPlaces;
        this.numberOfOpenParkingPassSpots = passPlaces;
    }

    public void updatePassPlaces(int amount){
        int passDifference = passPlaces - amount;
        int difference = passPlaces - amount;

        if(difference != 0){
            numberOfOpenSpots += difference;
            numberOfOpenParkingPassSpots -= passDifference;
            passPlaces = amount;
            System.out.println(passDifference + " : " + difference + " : " + numberOfOpenParkingPassSpots + " : " + numberOfOpenSpots);
        }
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
