package com.parkinglot;

import java.util.List;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return findParkingLotWithMostAvailableSlotRate()
                .orElseThrow(NoAvailableParkingPositionException::new)
                .parkCar(car);
    }

    private Optional<ParkingLot> findParkingLotWithMostAvailableSlotRate() {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((firstLot, secondLot) ->
                                calculateSlotRate(firstLot.getRemainingParkingLotSlots(), firstLot.getParkingLotSize())
                                >=
                                calculateSlotRate(secondLot.getRemainingParkingLotSlots(), secondLot.getParkingLotSize())
                                ?
                                firstLot : secondLot);
    }

    private float calculateSlotRate(int remainingSlot, int parkingLotSize) {
        return (float) remainingSlot / parkingLotSize;
    }
}
