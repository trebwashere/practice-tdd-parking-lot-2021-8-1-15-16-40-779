package com.parkinglot;

public class ParkingLot {
    private Car car;
    private ParkingTicket parkingTicket;
    private static ParkingLot parkingLot;

    private ParkingLot()
    {

    }

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

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
