package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLotManager extends StandardParkingBoy{

    private List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingLotManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public void setParkingBoyList(List<ParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    public ParkingTicket instructParkingBoyToParkCar(ParkingBoy parkingBoy, Car car) {
        return getParkingBoyFromList(parkingBoy)
                .park(car);
    }

    public Car instructParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return getParkingBoyFromList(parkingBoy)
                .fetch(parkingTicket);
    }

    private ParkingBoy getParkingBoyFromList(ParkingBoy parkingBoy) {
        Optional<ParkingBoy> presentParkingBoy = parkingBoyList.stream()
                .filter(parkBoy -> parkBoy.equals(parkingBoy))
                .findAny();

        return presentParkingBoy.orElse(null);
    }
}
