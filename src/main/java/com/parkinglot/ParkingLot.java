package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketAndCarMap = new HashMap<>();

    public ParkingTicket parkCar(Car car) {
        if (this.getParkingLotSlotSize() < 10) {
            ParkingTicket parkingTicket = new ParkingTicket();
            ticketAndCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(Customer customer,ParkingTicket parkingTicket) {
        if(customer.getParkingTicketList().contains(parkingTicket)) {
            Car fetchedCar = ticketAndCarMap.get(parkingTicket);
            customer.getParkingTicketList().remove(parkingTicket);
            ticketAndCarMap.remove(parkingTicket);
            return fetchedCar;
        }
        return null;
    }

    public int getParkingLotSlotSize() {
        return ticketAndCarMap.size();
    }
}
