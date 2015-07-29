package org.exceptions;

public class ParkingSpaceFullException extends RuntimeException {

    public ParkingSpaceFullException(String msg) {
        super(msg);
    }

}
