package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {

    private Car car;
    private ParkingLot parkingLot;
    private StandardParkingBoy standardParkingBoy;
    private List<ParkingLot> parkingLotList;
    private StandardParkingBoy standardParkingBoyWithParkingLotList;
    private SmartParkingBoy smartParkingBoyWithParkingLotList;
    private SuperSmartParkingBoy superSmartParkingBoyWithParkingLotList;


    @BeforeEach
    public void setup() {
        car = new Car();
        parkingLot = new ParkingLot();
        standardParkingBoy = new StandardParkingBoy(parkingLot);
        parkingLotList = Arrays.asList(parkingLot, parkingLot, parkingLot);
        standardParkingBoyWithParkingLotList = new StandardParkingBoy(parkingLotList);
        smartParkingBoyWithParkingLotList = new SmartParkingBoy(parkingLotList);
        superSmartParkingBoyWithParkingLotList = new SuperSmartParkingBoy(parkingLotList);
    }

    private void throwNoAvailableParkingPositionException() { throw new NoAvailableParkingPositionException(); }
    private void throwUnrecognizedParkingTicketException() { throw new UnrecognizedParkingTicketException(); }

    @Test
    public void should_return_parking_ticket_given_a_parking_lot_and_a_car() {

        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_given_a_parking_lot_with_parked_car_and_a_parking_ticket() {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Car returnedCar = parkingLot.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void should_not_fetch_car_given_a_parking_lot_with_parked_car_but_invalid_ticket() {
        parkingLot.parkCar(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_be_able_to_fetch_car_given_a_parking_lot_with_parked_car_but_reused_ticket() {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        parkingLot.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_allow_car_to_park_if_parking_lot_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
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

    @Test
    public void standardParkingBoy_should_return_parking_ticket_given_a_parking_lot_and_a_car() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void standardParkingBoy_should_return_car_when_fetch_given_a_parking_lot_with_parked_car_and_a_parking_ticket() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        Car returnedCar = standardParkingBoy.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void standardParkingBoy_should_not_fetch_car_given_a_parking_lot_with_parked_car_but_invalid_ticket() {
        standardParkingBoy.park(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_not_be_able_to_fetch_car_given_a_parking_lot_with_parked_car_but_reused_ticket() {
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_not_allow_car_to_park_if_parking_lot_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        StandardParkingBoy parkingBoyToBeSpied = new StandardParkingBoy(spyParkingLot);
        StandardParkingBoy mockStandardParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, () -> mockStandardParkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_return_parking_ticket_given_a_list_of_parking_lots_and_a_car() {
        ParkingTicket parkingTicket = standardParkingBoyWithParkingLotList.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void standardParkingBoy_should_return_car_when_fetch_given_a_list_of_parking_lots_with_parked_car_and_a_parking_ticket() {
        ParkingTicket parkingTicket = standardParkingBoyWithParkingLotList.park(car);
        Car returnedCar = standardParkingBoyWithParkingLotList.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void standardParkingBoy_should_not_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_invalid_ticket() {
        standardParkingBoyWithParkingLotList.park(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoyWithParkingLotList.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_not_be_able_to_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_reused_ticket() {
        ParkingTicket parkingTicket = standardParkingBoyWithParkingLotList.park(car);
        standardParkingBoyWithParkingLotList.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoyWithParkingLotList.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_not_allow_car_to_park_if_a_list_of_parking_lots_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        StandardParkingBoy parkingBoyToBeSpied = new StandardParkingBoy(Arrays.asList(spyParkingLot, spyParkingLot, spyParkingLot));
        StandardParkingBoy mockStandardParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, () -> mockStandardParkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void standardParkingBoy_should_park_car_at_next_available_parking_lot_if_first_parking_lot_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        StandardParkingBoy parkingBoyToBeSpied = new StandardParkingBoy(Arrays.asList(spyParkingLot, parkingLot));
        StandardParkingBoy mockStandardParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
        ParkingTicket parkingTicket = mockStandardParkingBoy.park(car);
        assertNotNull(parkingTicket);
        assertEquals(mockStandardParkingBoy.getParkingLots().get(1).fetch(parkingTicket), car);
    }

    @Test
    public void smartParkingBoy_should_return_parking_ticket_given_a_list_of_parking_lots_and_a_car() {
        ParkingTicket parkingTicket = smartParkingBoyWithParkingLotList.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void smartParkingBoy_should_return_car_when_fetch_given_a_list_of_parking_lots_with_parked_car_and_a_parking_ticket() {
        ParkingTicket parkingTicket = smartParkingBoyWithParkingLotList.park(car);
        Car returnedCar = smartParkingBoyWithParkingLotList.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void smartParkingBoy_should_not_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_invalid_ticket() {
        smartParkingBoyWithParkingLotList.park(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoyWithParkingLotList.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void smartParkingBoy_should_not_be_able_to_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_reused_ticket() {
        ParkingTicket parkingTicket = smartParkingBoyWithParkingLotList.park(car);
        smartParkingBoyWithParkingLotList.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoyWithParkingLotList.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void smartParkingBoy_should_not_allow_car_to_park_if_a_list_of_parking_lots_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        SmartParkingBoy parkingBoyToBeSpied = new SmartParkingBoy(Arrays.asList(spyParkingLot, spyParkingLot, spyParkingLot));
        SmartParkingBoy mockSmartParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, () -> mockSmartParkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void smartParkingBoy_should_park_car_at_parking_lot_with_most_slots_given_a_list_of_parking_lots() {
        ParkingLot firstSpyParkingLot = Mockito.spy(ParkingLot.class);
        ParkingLot secondSpyParkingLot = Mockito.spy(ParkingLot.class);
        ParkingLot thirdSpyParkingLot = Mockito.spy(ParkingLot.class);
        SmartParkingBoy parkingBoyToBeSpied = new SmartParkingBoy(Arrays.asList(firstSpyParkingLot, secondSpyParkingLot, thirdSpyParkingLot));
        SmartParkingBoy mockSmartParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(firstSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(0);
        Mockito.lenient().when(secondSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(10);
        Mockito.lenient().when(thirdSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(5);
        ParkingTicket parkingTicket = mockSmartParkingBoy.park(car);
        assertNotNull(parkingTicket);
        assertEquals(mockSmartParkingBoy.getParkingLots().get(1).fetch(parkingTicket), car);
    }

    @Test
    public void superSmartParkingBoy_should_return_parking_ticket_given_a_list_of_parking_lots_and_a_car() {
        ParkingTicket parkingTicket = superSmartParkingBoyWithParkingLotList.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void superSmartParkingBoy_should_return_car_when_fetch_given_a_list_of_parking_lots_with_parked_car_and_a_parking_ticket() {
        ParkingTicket parkingTicket = superSmartParkingBoyWithParkingLotList.park(car);
        Car returnedCar = superSmartParkingBoyWithParkingLotList.fetch(parkingTicket);

        assertEquals(returnedCar, car);
    }

    @Test
    public void superSmartParkingBoy_should_not_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_invalid_ticket() {
        superSmartParkingBoyWithParkingLotList.park(car);
        ParkingTicket fakeParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoyWithParkingLotList.fetch(fakeParkingTicket));

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void superSmartParkingBoy_should_not_be_able_to_fetch_car_given_a_list_of_parking_lots_with_parked_car_but_reused_ticket() {
        ParkingTicket parkingTicket = superSmartParkingBoyWithParkingLotList.park(car);
        superSmartParkingBoyWithParkingLotList.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoyWithParkingLotList.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void superSmartParkingBoy_should_not_allow_car_to_park_if_a_list_of_parking_lots_is_full() {
        ParkingLot spyParkingLot = Mockito.spy(ParkingLot.class);
        SuperSmartParkingBoy parkingBoyToBeSpied = new SuperSmartParkingBoy(Arrays.asList(spyParkingLot, spyParkingLot, spyParkingLot));
        SuperSmartParkingBoy mockSuperSmartParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        Mockito.lenient().when(spyParkingLot.isFull()).thenReturn(true);
        Exception exception = assertThrows(NoAvailableParkingPositionException.class, () -> mockSuperSmartParkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void superSmartParkingBoy_should_park_car_at_parking_lot_with_most_slot_rate_given_a_list_of_parking_lots() {
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(20);
        ParkingLot thirdParkingLot = new ParkingLot(100);
        ParkingLot firstSpyParkingLot = Mockito.spy(firstParkingLot);
        ParkingLot secondSpyParkingLot = Mockito.spy(secondParkingLot);
        ParkingLot thirdSpyParkingLot = Mockito.spy(thirdParkingLot);
        Mockito.lenient().when(firstSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(1);
        Mockito.lenient().when(secondSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(15);
        Mockito.lenient().when(thirdSpyParkingLot.getRemainingParkingLotSlots()).thenReturn(50);

        SuperSmartParkingBoy parkingBoyToBeSpied = new SuperSmartParkingBoy(Arrays.asList(firstSpyParkingLot, secondSpyParkingLot, thirdSpyParkingLot));
        SuperSmartParkingBoy mockSuperSmartParkingBoy = Mockito.spy(parkingBoyToBeSpied);
        ParkingTicket parkingTicket = mockSuperSmartParkingBoy.park(car);
        assertNotNull(parkingTicket);
        assertEquals(mockSuperSmartParkingBoy.getParkingLots().get(1).fetch(parkingTicket), car);
    }

}
