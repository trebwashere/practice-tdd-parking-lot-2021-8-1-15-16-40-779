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

    public Car fetch(Customer customer,ParkingTicket parkingTicket) {
        Car fetchedCar = ticketAndCarMap.get(parkingTicket);
        if (fetchedCar != null) {
            ticketAndCarMap.remove(parkingTicket);
        }
        return fetchedCar;
    }

    public int getParkingLotSlotSize() {
        return ticketAndCarMap.size();
    }
}
