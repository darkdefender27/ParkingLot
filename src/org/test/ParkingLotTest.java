package org.test;

import org.bmag.Car;
import org.bmag.PLObserver;
import org.bmag.PLOwner;
import org.bmag.ParkingLot;
import org.junit.Before;
import org.junit.Test;
import org.org.exceptions.CarNotFoundException;
import org.org.exceptions.LotFullException;
import org.org.exceptions.UniqueCarException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Before
    public void setUp() {
        PLOwner plOwner;
    }

    @Test
    public void testParkSuccess() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 2, 0);

        Car c1 = new Car(1, "G07");
        int token1 = p1.park(c1);
        assertEquals(1, token1);

        Car c2 = new Car(2, "G08");
        int token2 = p1.park(c2);
        assertEquals(2, token2);
    }

    @Test(expected = LotFullException.class)
    public void testParkFail() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 2, 0);

        p1.park(new Car(1, "G07"));
        p1.park(new Car(2, "G08"));
        p1.park(new Car(3, "H08"));
    }

    @Test(expected = UniqueCarException.class)
    public void testParkDuplicate() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p = new ParkingLot(plOwner, 2, 0);

        p.park(new Car(1, "G07"));
        p.park(new Car(1, "G07"));

    }

    @Test(expected = CarNotFoundException.class)
    public void testUnparkFail() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p = new ParkingLot(plOwner, 2, 0);

        Car c = p.unPark(1);
    }

    @Test
    public void testUnparkIfCarExists() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p = new ParkingLot(plOwner, 2, 0);

        Car c1 = new Car(1, "G09");
        p.park(c1);

        Car c2 = p.unPark(1);
        assertEquals(c1, c2);
    }

    /*@Test
    public void testOnFullNotification() {
        //TestPLOwner plOwner = new TestPLOwner();
        TestPLObserver plOwner = new TestPLOwner();
        ParkingLot p = new ParkingLot(plOwner, 1, 0);

        Car c1 = new Car(1, "G09");
        p.park(c1);

        //assertTrue(plOwner.isStatus());
    }

    @Test
    public void testOnSpaceAvailabilityNotification() {
        TestPLOwner plOwner = new TestPLOwner();
        ParkingLot p = new ParkingLot(plOwner, 1, 0);

        Car c1 = new Car(1, "G09");
        p.park(c1);
        Car c2 = p.unPark(1);

        assertFalse(plOwner.isStatus());
    }*/

    @Test
    public void testOnFullNotifyAll() {
        List<PLObserver> notifierList = new ArrayList<PLObserver>();

        PLObserver per1 = new TestPLOwner();
        PLObserver per2 = new TestFBIAgent();
        PLObserver per3 = new TestFBIAgent();

        notifierList.add(per1);
        notifierList.add(per2);
        notifierList.add(per3);

        ParkingLot p = new ParkingLot(per1, 1, 0);
        p.register(notifierList);

        Car c1 = new Car(1, "G09");
        p.park(c1);

        for(PLObserver person: notifierList) {
            assertTrue(person.checkStatus());
        }

    }

    @Test
    public void testOnSpaceAvailableNotifyAll() {
        List<PLObserver> notifierList = new ArrayList<PLObserver>();

        PLObserver per1 = new TestPLOwner();
        PLObserver per2 = new TestFBIAgent();
        PLObserver per3 = new TestFBIAgent();

        notifierList.add(per1);
        notifierList.add(per2);
        notifierList.add(per3);

        ParkingLot p = new ParkingLot(per1, 1, 0);
        p.register(notifierList);

        Car c1 = new Car(1, "G09");
        p.park(c1);
        p.unPark(1);

        for(PLObserver person: notifierList) {
            assertFalse(person.checkStatus());
        }
    }


}