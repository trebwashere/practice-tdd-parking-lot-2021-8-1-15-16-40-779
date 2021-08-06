package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {

    private ParkingLot parkingLot;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    private ParkingLot mockParkingLot;

    @BeforeEach
    public void setup() {
        parkingLot = new ParkingLot();
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_return_parking_ticket_given_a_parking_lot_and_a_car() {
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_parking_lot_with_parked_car_and_a_parking_ticket() {
        Customer customer = new Customer();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        customer.addParkingTicket(parkingTicket);
        Car returnedCar = parkingLot.fetch(customer,parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void should_not_allow_car_to_park_if_parking_lot_is_full() {
        Car car = new Car();
        Mockito.lenient().when(mockParkingLot.getParkingLotSlotSize()).thenReturn(10);
        ParkingTicket parkingTicket = mockParkingLot.parkCar(car);

        assertNull(parkingTicket);
    }

    @Test
    public void should_not_fetch_car_given_a_parking_lot_with_parked_car_but_invalid_ticket() {
        Customer customer = new Customer();
        Car car = new Car();
        parkingLot.parkCar(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Car outputCar = parkingLot.fetch(customer,fakeParkingTicket);

        assertNull(outputCar);
    }

    @Test
    public void should_not_be_able_to_fetch_car_given_a_parking_lot_with_parked_car_but_reused_ticket() {
        Customer customer = new Customer();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        customer.addParkingTicket(parkingTicket);
        parkingLot.fetch(customer,parkingTicket);
        Car reusedTicketForCar = parkingLot.fetch(customer,parkingTicket);
        assertNull(reusedTicketForCar);
    }

    @Test
    public void should_not_be_able_to_fetch_car_of_another_customer_if_ticket_not_owned_by_fetching_customer_was_used() {
        Customer bert = new Customer();
        Customer david = new Customer();

        Car bertCar = new Car();
        Car davidCar = new Car();

        ParkingTicket bertParkingTicket = parkingLot.parkCar(bertCar);
        bert.addParkingTicket(bertParkingTicket);
        ParkingTicket davidParkingTicket = parkingLot.parkCar(davidCar);
        david.addParkingTicket(davidParkingTicket);

        Car bertWithDavidTicket = parkingLot.fetch(bert, davidParkingTicket);
        Car davidWithBertTicket = parkingLot.fetch(david, bertParkingTicket);

        assertNull(bertWithDavidTicket);
        assertNull(davidWithBertTicket);
    }

    @Test
    public void should_display_Unrecognized_parking_ticket_when_car_is_fetched_but_no_car_was_released() {
        should_not_fetch_car_given_a_parking_lot_with_parked_car_but_invalid_ticket();
        assertTrue(systemOut().contains("Unrecognized parking ticket."));
    }

    @Test
    public void should_display_No_Position_Available_given_parkCar_is_called_but_parking_lot_is_already_full() {
        Car car = new Car();
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        Mockito.lenient().when(spyParkingLot.getParkingLotSlotSize()).thenReturn(10);
        spyParkingLot.parkCar(car);
        assertTrue(systemOut().contains("No available position."));
    }
}
