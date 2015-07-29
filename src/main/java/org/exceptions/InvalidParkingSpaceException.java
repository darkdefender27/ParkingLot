package org.exceptions;

public class InvalidParkingSpaceException extends RuntimeException {

    public InvalidParkingSpaceException(String msg) {
        super(msg);
    }
}
