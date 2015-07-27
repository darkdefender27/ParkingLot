package org.bmag;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String msg) {
        super(msg);
    }
}
