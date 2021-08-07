package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StandardParkingBoy {

    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLotList;
    }

    public ParkingTicket park(Car car) {
        return findFirstAvailableParkingLot()
                .parkCar(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return findParkingLotRelatedToTicket(parkingTicket)
                .fetch(parkingTicket);
    }

    private ParkingLot findParkingLotRelatedToTicket(ParkingTicket parkingTicket) {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.isTicketAtCurrentParkingLot(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }

    private ParkingLot findFirstAvailableParkingLot() {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NoAvailableParkingPositionException::new);
    }
}
