package org.test;

import org.bmag.*;
import org.exceptions.CarNotFoundException;
import org.exceptions.ParkingSpaceFullException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AttendantTest {

    @Test
    public void testParkSuccess() throws Exception {
        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 1, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 1, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);
        assertEquals("0-1", tokenGenerated1);

        Car c2 = new Car(2, "G08");
        String tokenGenerated2 = attendant.park(c2);
        assertEquals("1-1", tokenGenerated2);

        Car c = attendant.unPark("0-1");

        Car c3 = new Car(2, "G08");
        String tokenGenerated3 = attendant.park(c3);
        assertEquals("0-2", tokenGenerated3);

    }

    @Test(expected = ParkingSpaceFullException.class)
    public void testParkFailure() {

        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 1, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 1, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);

        Car c2 = new Car(2, "G08");
        String tokenGenerated2 = attendant.park(c2);

        Car c3 = new Car(2, "G08");
        String tokenGenerated3 = attendant.park(c3);

    }


    @Test
    public void testUnParkSuccess() throws Exception {

        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 1, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 1, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);
        Car c = attendant.unPark("0-1");

        assertEquals(c1, c);
    }

    @Test(expected = CarNotFoundException.class)
    public void testUnParkFailure() {

        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 1, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 1, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);
        Car c = attendant.unPark("1-1");

    }

    @Test
    public void testMaxSpacedParkingLot() {

        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 1, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 2, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);

        assertEquals("1-1", tokenGenerated1);

    }

    @Test(expected = ParkingSpaceFullException.class)
    public void testMaxSpacedParkingLotFailure() {


        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 0, 0);
        ParkingLot p2 = new ParkingLot(plOwner, 0, 0);

        List<ParkingLot> plotList = new ArrayList<>();

        plotList.add(p1);
        plotList.add(p2);

        ParkingSpace pSpace = new ParkingSpace(plotList);
        Attendant attendant = new Attendant(pSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);

    }
}