package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticket_given_a_parking_lot_and_a_car() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        assertNotNull(parkingTicket);
    }
}
