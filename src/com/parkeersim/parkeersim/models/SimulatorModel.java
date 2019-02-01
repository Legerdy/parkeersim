package com.parkeersim.parkeersim.models;

import com.parkeersim.mvc.BaseModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SimulatorModel extends BaseModel {
    private static final String AD_HOC = "1";
    private static final String PASS = "2";
    private static final String RES = "3";

    private boolean isPaused = true;

    private GarageModel garagemodel;

    private int workers = 10;
    private int salary = 15;
    private static final int energycost = 2;
    private int expanses = 0;
    private static final double taxes = 0.42;
    private double weeklyIncome = 0;
    private double tempWeeklyIncome = 0;
    private double totalTaxes = 0;
    private double revenue = 0;

    private int angryCustomers = 0;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue entranceReservationQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int week = 0;

    private int tickPause = 100;
    private int passPlaces = 50;
    private int amount = passPlaces;

    private double money;

    int weekDayArrivals= 110; // average number of arriving cars per hour
    int weekendArrivals = 262; // average number of arriving cars per hour
    int weekDayPassArrivals= 60; // average number of arriving cars per hour
    int weekendPassArrivals = 75; // average number of arriving cars per hour
    int weekDayReservationArrivals = 30;
    int weekendReservationArrivals = 40;

    int enterSpeed = 4; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public SimulatorModel(GarageModel garagemodel) {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReservationQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.garagemodel = garagemodel;
    }

    public void run() {
        Thread thread = new Thread(() -> {
            while (true){
                if(isPaused){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    tick();
                }
            }
        });
        thread.start();
    }

    public void setPause(boolean state){
        isPaused = state;
    }

    public void setTickPause(int newTickPause){
        this.tickPause = newTickPause;
    }

    public int getTickPause(){
        return tickPause;
    }


    private void tick() {
        advanceTime();
        handleExit();
        updateViews();
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
        garagemodel.updatePassPlaces(amount);
        this.passPlaces = amount;
    }

    private void payExpanses(){
        int expenses = (7*24*(workers * salary + energycost));
        double tax = weeklyIncome * taxes;

        tempWeeklyIncome = weeklyIncome;
        expanses = expanses + expenses + (int)tax;
        totalTaxes += tax;
        revenue += (tempWeeklyIncome - expenses - (int)tax);
        money = money - expenses - tax;
    }

    public int getWeek() {
        return week;
    }

    public int getDay(){
        return day;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public double getBalance(){
        return money;
    }

    public int getExpanses() {
        return expanses;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public int getRevenue() {
        return (int)revenue;
    }

    public int getTempWeeklyIncome() {
        return (int)tempWeeklyIncome;
    }



    public int getOpenParkingPassSpots(){
        return garagemodel.getNumberOfOpenParkingPassSpots();
    }

    public int getOpenSpots(){
        return garagemodel.getNumberOfOpenSpots();
    }

    public int getAllSpots(){
        return garagemodel.getNumberOfRows() * garagemodel.getNumberOfFloors() * garagemodel.getNumberOfPlaces();
    }

    public int getPassPlaces(){
        return passPlaces;
    }

    public int getNumberOfAdHocCars(){
        return garagemodel.getNumberOfAdHocCars();
    }

    public int getNumberOfParkingPassCars(){
        return garagemodel.getNumberOfParkingPassCars();
    }

    public int getNumberOfReservationCars(){
        return garagemodel.getNumberOfReservationCars();
    }

    public int getTotalNumberOfOpenParkingSpaces(){
        return getOpenParkingPassSpots() + getOpenSpots();
    }

    public int getAngryCustomers(){
        return angryCustomers;
    }

    public void setPassSpaces(int amount){
        this.amount = amount;
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
            week++;
            if(money >= 500 && week > 0 && day == 0) {
                payExpanses();
                weeklyIncome = 0;
            }
        }
    }

    private void handleEntrance(){
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
        carsEntering(entranceReservationQueue);
    }

    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private void updateViews(){
        garagemodel.tick();
        // Update the car park view.
        garagemodel.updateView();
    }

    /**
     * Method for adding new arriving cars
     */
    private void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(weekDayReservationArrivals, weekendReservationArrivals);
        addArrivingCars(numberOfCars, RES);
    }

    /**
     * This method takes the first car in the queue and assigns it to a parking space
     * @param queue
     */
    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 && i<enterSpeed) {
            Car car = queue.removeCar();
            if(car.getTypeId() == 1 && garagemodel.getNumberOfOpenParkingPassSpots()>0){
                Location freeLocation = garagemodel.getFirstFreeParkingPassLocation();
                garagemodel.setCarAt(freeLocation, car);
            } else if (garagemodel.getNumberOfOpenSpots()>0){
                Location freeLocation = garagemodel.getFirstFreeLocation();
                garagemodel.setCarAt(freeLocation, car);
            }
            i++;
        }

        Iterator cars = queue.iterator();
        ArrayList<Car> removeCars = new ArrayList<>();
        while(cars.hasNext()){
            Car car = (Car)cars.next();
            if(car.getQueueTime() > car.getMaxWaitTime()){
                removeCars.add(car);
                angryCustomers++;
            } else {
                car.tickQueueTime();
            }
        }

        for(Car car : removeCars){
            queue.removeSpecificCar(car);
        }
    }

        private void carsReadyToLeave(){
            // Add leaving cars to the payment queue.
            Car car = garagemodel.getFirstLeavingCar();
            while (car!=null) {
                if (car.getHasToPay()){
                    car.setIsPaying(true);
                    paymentCarQueue.addCar(car);
                }
                else {
                    carLeavesSpot(car);
                }
                car = garagemodel.getFirstLeavingCar();
            }
        }

        private void carsPaying(){
            // Let cars pay.
            int i=0;
            while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
                Car car = paymentCarQueue.removeCar();
                double priceRounded = Math.round(car.getStayTime() * 0.025 * 100);
                double price = priceRounded/100;
                if(car.getTypeId() == 2){
                    money += price * 1.5;
                    weeklyIncome += (price * 1.5);
                }
                else if(car.getTypeId() == 1){
                    money += price * 1.25;
                    weeklyIncome += (price * 1.25);
                }
                else {
                    money += price;
                    weeklyIncome += (price);
                }
                carLeavesSpot(car);
                i++;
            }
        }

        private void carsLeaving(){
            // Let cars leave.
            int i=0;
            while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
                exitCarQueue.removeCar();
                i++;
            }
        }

        /**
         * Method that calculates an amount of cars that enter a queue
         * @param weekDay
         * @param weekend
         * @return
         */
        private int getNumberOfCars(int weekDay, int weekend){
            Random random = new Random();

            // Get the average number of cars that arrive per hour.
            int averageNumberOfCarsPerHour;
            if(day < 5){
                if (hour < 6){
                    averageNumberOfCarsPerHour = (weekDay / 10) * 4;
                } else if(hour < 11){
                    averageNumberOfCarsPerHour = (weekDay / 10) * 7;
                } else {
                    averageNumberOfCarsPerHour = weekDay;
                }
            } else {
                if (hour < 6){
                    averageNumberOfCarsPerHour = (weekend / 10) * 4;
                } else if(hour < 11){
                    averageNumberOfCarsPerHour = (weekend / 10) * 7;
                } else {
                    averageNumberOfCarsPerHour = weekend;
                }
            }

            // Calculate the number of cars that arrive this minute.
            double standardDeviation = averageNumberOfCarsPerHour * 0.3;
            double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
            return (int)Math.round(numberOfCarsPerHour / 60);
        }

        /**
         * Method that adds new cars and puts them in the corresponding queue
         * @param numberOfCars
         * @param type
         */
        private void addArrivingCars(int numberOfCars, String type){
            // Add the cars to the back of the queue.
            switch(type) {
                case AD_HOC:
                    for (int i = 0; i < numberOfCars; i++) {
                        entranceCarQueue.addCar(new AdHocCar());
                }
                break;
                case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
                case RES:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceReservationQueue.addCar(new ReservationCar());
                }
                break;
        }
    }

    private void carLeavesSpot(Car car){
        garagemodel.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}
