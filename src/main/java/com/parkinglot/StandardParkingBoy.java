package com.parkinglot;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return findFirstAvailableParkingLot()
                .parkCar(car);
    }

    private ParkingLot findFirstAvailableParkingLot() {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NoAvailableParkingPositionException::new);
    }
}
