package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<ParkingTicket> parkingTicketList = new ArrayList<>();
    public void addParkingTicket(ParkingTicket parkingTicket) {
        parkingTicketList.add(parkingTicket);
    }
}
