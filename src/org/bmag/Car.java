package org.bmag;

public class Car {

    private int carId;
    private String carNumber;

    public Car() {}

    public int getCarId() {
        return carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public Car(int carId, String carNumber) {
        this.carId = carId;
        this.carNumber = carNumber;
    }
}
