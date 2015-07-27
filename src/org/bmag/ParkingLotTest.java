package org.bmag;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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

    @Test(expected = org.bmag.LotFullException.class)
    public void testParkFail() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p1 = new ParkingLot(plOwner, 2, 0);

        p1.park(new Car(1, "G07"));
        p1.park(new Car(2, "G08"));
        p1.park(new Car(3, "H08"));
    }

    @Test(expected = org.bmag.UniqueCarException.class)
    public void testParkDuplicate() {
        PLOwner plOwner=new PLOwner();
        ParkingLot p = new ParkingLot(plOwner, 2, 0);

        p.park(new Car(1, "G07"));
        p.park(new Car(1, "G07"));

    }

    @Test(expected = org.bmag.CarNotFoundException.class)
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

    @Test
    public void testOnFullNotification() {
        TestPLOwner plOwner = new TestPLOwner();
        ParkingLot p = new ParkingLot(plOwner, 1, 0);

        Car c1 = new Car(1, "G09");
        p.park(c1);

        assertTrue(plOwner.isStatus());
    }
}