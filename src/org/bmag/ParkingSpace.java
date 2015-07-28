package org.bmag;


import java.util.List;

public class ParkingSpace {

    private List<ParkingLot> plList;

    public ParkingSpace(List<ParkingLot> plList) {
        this.plList = plList;
    }

    public List<ParkingLot> getPlList() {
        return plList;
    }

}
