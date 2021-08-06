package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int parkingLotSize = 10;
    private final Map<ParkingTicket, Car> ticketAndCarMap = new HashMap<>();

    public ParkingLot(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    public ParkingLot() {
    }

    public ParkingTicket parkCar(Car car) {
        if (this.getParkingLotSlotSize() < parkingLotSize) {
            ParkingTicket parkingTicket = new ParkingTicket();
            ticketAndCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
        throw new NoAvailableParkingPositionException();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = ticketAndCarMap.get(parkingTicket);
        if (fetchedCar == null) {
            throw new UnrecognizedParkingTicketException();
        }
        ticketAndCarMap.remove(parkingTicket);
        return fetchedCar;
    }

    public int getParkingLotSlotSize() {
        return ticketAndCarMap.size();
    }
}
