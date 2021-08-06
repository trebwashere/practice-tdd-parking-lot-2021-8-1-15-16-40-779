package com.parkinglot;

public class NoAvailableParkingPositionException extends RuntimeException{
    @Override
    public String getMessage() {
        return "No available position.";
    }
}
