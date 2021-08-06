package com.parkinglot;

public class ParkingLot {
    public ParkingTicket parkCar(Car car) {
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return new Car();
    }
}
