package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    public void setup() {
        parkingLot = new ParkingLot();
    }

    private void throwNoAvailableParkingPositionException() { throw new NoAvailableParkingPositionException(); }
    private void throwUnrecognizedParkingTicketException() { throw new UnrecognizedParkingTicketException(); }

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

    @Test
    public void should_not_fetch_car_given_a_parking_lot_with_parked_car_but_invalid_ticket() {
        Car car = new Car();
        parkingLot.parkCar(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_be_able_to_fetch_car_given_a_parking_lot_with_parked_car_but_reused_ticket() {
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        parkingLot.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_allow_car_to_park_if_parking_lot_is_full() {
        Car car = new Car();
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        Mockito.lenient().when(spyParkingLot.getParkingLotSlotSize()).thenReturn(10);
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, () -> spyParkingLot.parkCar(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_display_Unrecognized_parking_ticket_when_car_is_fetched_but_no_car_was_released() {
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, this::throwUnrecognizedParkingTicketException);
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_display_No_Position_Available_given_parkCar_is_called_but_parking_lot_is_already_full() {
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, this::throwNoAvailableParkingPositionException);
        assertEquals("No available position.", exception.getMessage());
    }
}
