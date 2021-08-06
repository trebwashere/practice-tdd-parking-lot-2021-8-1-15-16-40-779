package com.parkinglot;

public class ParkingLot {
    private Car car;
    private ParkingTicket parkingTicket;
    public ParkingTicket parkCar(Car car) {
        this.car = car;
        this.parkingTicket = new ParkingTicket();
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket.equals(this.parkingTicket)) {
            return car;
        }
        return null;
    }
}
