package org.bmag;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void testParkSuccess() {
        ParkingLot p1 = new ParkingLot(2, 0);

        Car c1 = new Car(1, "G07");
        int token1 = p1.park(c1);
        assertEquals(1, token1);

        Car c2 = new Car(2, "G08");
        int token2 = p1.park(c2);
        assertEquals(2, token2);
    }

    @Test(expected = org.bmag.LotFullException.class)
    public void testParkFail() {
        ParkingLot p1 = new ParkingLot(2, 0);

        p1.park(new Car(1, "G07"));
        p1.park(new Car(2, "G08"));
        p1.park(new Car(3, "H08"));
    }

    @Test(expected = org.bmag.UniqueCarException.class)
    public void testParkDuplicate() {
        ParkingLot p = new ParkingLot(2, 0);

        p.park(new Car(1, "G07"));
        p.park(new Car(1, "G07"));

    }
}