package org.bmag;

import org.exceptions.CarNotFoundException;
import org.exceptions.ParkingSpaceFullException;

import java.util.List;

public class Attendant {

    ParkingSpace pSpace;

    public Attendant(ParkingSpace pSapce) {
        this.pSpace = pSapce;
    }

    public String park(Car c) {
        String tokenString="";

        int plotNumber;
        int tokenNumber;

        List<ParkingLot> plList = pSpace.getPlList();


        for(ParkingLot pLot : plList) {
            if(pLot.isSpaceAvailable()) {
                plotNumber = plList.indexOf(pLot);
                tokenNumber = pLot.park(c);
                tokenString = plotNumber + "-" + tokenNumber;
                break;
            }
        }

        if(tokenString.equals("")) {
            throw new ParkingSpaceFullException("GO!! GO!!!");
        }

        return tokenString;
    }

    public Car unPark(String carToken) {

        String[] args = carToken.split("-");

        int plotNumber = Integer.parseInt(args[0]);
        int tokenNumber = Integer.parseInt(args[1]);

        if(plotNumber >= this.pSpace.getPlList().size()) {
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
