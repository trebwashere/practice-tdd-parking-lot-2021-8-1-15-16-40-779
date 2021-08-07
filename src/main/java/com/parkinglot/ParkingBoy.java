package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLotList;
    }

    abstract ParkingTicket park(Car car);

    abstract Car fetch (ParkingTicket parkingTicket);
}
