package com.parkinglot;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return findParkingLotWithMostSlots()
                .orElseThrow(NoAvailableParkingPositionException::new)
                .parkCar(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return findParkingLotRelatedToTicket(parkingTicket)
                .fetch(parkingTicket);
    }

    private Optional<ParkingLot> findParkingLotWithMostSlots() {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((firstLot, secondLot) ->
                        firstLot.getRemainingParkingLotSlots() >= secondLot.getRemainingParkingLotSlots()
                                ?
                                firstLot : secondLot);
    }
}
