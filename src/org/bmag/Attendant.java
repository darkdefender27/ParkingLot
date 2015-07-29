package org.bmag;

import org.exceptions.CarNotFoundException;
import org.exceptions.ParkingSpaceFullException;

import java.util.List;

public class Attendant {

    ParkingSpace pSpace;

    public Attendant(ParkingSpace pSapce) {
        this.pSpace = pSapce;
    }

    public ParkingLot chooseParkingLot() {

        List<ParkingLot> plList = pSpace.getPlList();

        if(plList.size()>0) {

            ParkingLot maxSpacedPL = null;
            int maxSpace = 0;

            for(ParkingLot pLot : plList) {
                if(pLot.isSpaceAvailable()) {
                    int newMaxSpace = pLot.getAvailableLotSpace();
                    if(newMaxSpace>maxSpace) {
                        maxSpace = newMaxSpace;
                        maxSpacedPL = pLot;
                    }
                }
            }

            if(maxSpace == 0) {
                throw new ParkingSpaceFullException("PS is FULL!!");
            }
            else {
                return maxSpacedPL;
            }
        }
        else {
            throw new ParkingSpaceFullException("NO Parking Lot Available!");
        }

    }

    public String park(Car c) {

        int plotNumber;
        int tokenNumber;

        List<ParkingLot> plList = pSpace.getPlList();

        ParkingLot pLot = chooseParkingLot();

        plotNumber = plList.indexOf(pLot);
        tokenNumber = pLot.park(c);

        return plotNumber + "-" + tokenNumber;
    }

    public Car unPark(String carToken) {

        String[] args = carToken.split("-");

        int plotNumber = Integer.parseInt(args[0]);
        int tokenNumber = Integer.parseInt(args[1]);

        if(plotNumber >= this.pSpace.getAvailablePLSize()) {
            throw new CarNotFoundException("CAR NOT FOUND!!");
        }
        else {
            List<ParkingLot> plList = pSpace.getPlList();
            ParkingLot pLot = plList.get(plotNumber);

            Car c = pLot.unPark(tokenNumber);

            return c;
        }
    }

}
