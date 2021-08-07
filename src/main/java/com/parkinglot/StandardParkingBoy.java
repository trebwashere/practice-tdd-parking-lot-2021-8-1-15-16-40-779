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

    public Car fetch(ParkingTicket parkingTicket) {
        return findParkingLotRelatedToTicket(parkingTicket)
                .fetch(parkingTicket);
    }

    private ParkingLot findParkingLotRelatedToTicket(ParkingTicket parkingTicket) {
        return getParkingLots().stream()
                .filter(parkingLot -> parkingLot.isTicketAtCurrentParkingLot(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }

    private ParkingLot findFirstAvailableParkingLot() {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NoAvailableParkingPositionException::new);
    }
}
