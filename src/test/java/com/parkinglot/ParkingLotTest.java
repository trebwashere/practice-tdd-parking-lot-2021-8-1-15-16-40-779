package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    public void setup() {
        parkingLot = ParkingLot.getInstance();
    }
    @Test
    public void should_return_parking_ticket_given_a_parking_lot_and_a_car() {
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_parking_lot_with_parked_car_and_a_parking_ticket() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Car returnedCar = parkingLot.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }
}
