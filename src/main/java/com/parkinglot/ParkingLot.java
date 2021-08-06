package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car;
    private static ParkingLot parkingLot;

    private Map<ParkingTicket, Car> ticketAndCarMap = new HashMap<>();

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
        if (parkingLot.getParkingLotSlotSize() < 10) {
            ParkingTicket parkingTicket = new ParkingTicket();
            ticketAndCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return ticketAndCarMap.get(parkingTicket);
    }

    public int getParkingLotSlotSize() {
        return ticketAndCarMap.size();
    }
}
