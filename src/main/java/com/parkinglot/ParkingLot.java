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
        if (!this.isFull()) {
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

    public boolean isFull() {
        return parkingLotSize < ticketAndCarMap.size();
    }

    public boolean isTicketAtCurrentParkingLot(ParkingTicket parkingTicket) {
        return ticketAndCarMap.containsKey(parkingTicket);
    }

    public int getRemainingParkingLotSlots() {
        return parkingLotSize - ticketAndCarMap.size();
    }

    public int getParkingLotSize() {
        return parkingLotSize;
    }

    public Map<ParkingTicket, Car> getTicketAndCarMap() {
        return ticketAndCarMap;
    }
}
