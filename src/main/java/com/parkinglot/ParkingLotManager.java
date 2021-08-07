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
        ParkingBoy parkingBoyToBeInstructed = getParkingBoyInList(parkingBoy);
        if (parkingBoyToBeInstructed != null) {
                return parkingBoyToBeInstructed.park(car);
        }
        return null;
    }

    public Car instructParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        ParkingBoy parkingBoyInList = getParkingBoyInList(parkingBoy);
        if (parkingBoyInList != null) {
            return parkingBoyInList.fetch(parkingTicket);
        }
        return null;
    }

    private ParkingBoy getParkingBoyInList(ParkingBoy parkingBoy) {
        return parkingBoyList.stream()
                .filter(parkBoy -> parkBoy.equals(parkingBoy))
                .findAny()
                .orElse(null);
    }
}
