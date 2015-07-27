package org.bmag;

public class Car {

    private int carId;
    private String carNumber;

    public Car() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getCarId() != car.getCarId()) return false;
        return getCarNumber().equals(car.getCarNumber());

    }

    @Override
    public int hashCode() {
        int result = getCarId();
        result = 31 * result + getCarNumber().hashCode();
        return result;
    }

    /*@Override
    public boolean equals(Object obj) {

        boolean result=false;
        Car c1 = (Car) obj;

        if(c1.carId==this.carId) {
            result=true;
        }

        return result;
    }*/
}
