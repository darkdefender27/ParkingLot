package org.test;

import org.bmag.*;
import org.junit.Before;
import org.junit.Test;
import org.exceptions.CarNotFoundException;
import org.exceptions.LotFullException;
import org.exceptions.UniqueCarException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        TestPLObserver per1 = new TestPLOwner();
        TestPLObserver per2 = new TestFBIAgent();
        TestPLObserver per3 = new TestFBIAgent();

        ParkingLot p = new ParkingLot(per1, 1, 0);

        p.subscribe(per1, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.FULL) {
                    return true;
                } else if (eventCode == PLEventEnum.VACANT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        p.subscribe(per2, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.EIGHTYCENT) {
                    return true;
                } else {
                    return false;
                }
            }
        });


        Car c1 = new Car(1, "G09");
        p.park(c1);

        assertEquals(PLEventEnum.FULL, per1.checkStatus());
        assertNotEquals(PLEventEnum.FULL, per2.checkStatus());

    }

    @Test
    public void testOnSpaceAvailableNotifyAll() {
        List<PLObserver> notifierList = new ArrayList<PLObserver>();

        TestPLObserver per1 = new TestPLOwner();
        TestPLObserver per2 = new TestFBIAgent();
        TestPLObserver per3 = new TestFBIAgent();

        ParkingLot p = new ParkingLot(per1, 1, 0);

        p.subscribe(per1, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.FULL) {
                    return true;
                } else if (eventCode == PLEventEnum.VACANT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        p.subscribe(per2, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.EIGHTYCENT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        Car c1 = new Car(1, "G09");
        p.park(c1);
        p.unPark(1);

        assertEquals(PLEventEnum.VACANT, per1.checkStatus());
        assertNotEquals(PLEventEnum.VACANT, per2.checkStatus());

    }

    @Test
    public void testEightyPerCentNotification() {

        Map<PLObserver, SubscriptionStrategy> obvList = new HashMap<>();

        TestPLObserver per1 = new TestPLOwner();
        TestPLObserver per2 = new TestFBIAgent();
        TestPLObserver per3 = new TestFBIAgent();

        ParkingLot p = new ParkingLot(per1, 1, 0);


        p.subscribe(per1, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.FULL) {
                    return true;
                } else if (eventCode == PLEventEnum.VACANT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        p.subscribe(per2, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.EIGHTYCENT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        p.subscribe(per3, new SubscriptionStrategy() {
            @Override
            public boolean apply(PLEventEnum eventCode) {
                if (eventCode == PLEventEnum.EIGHTYCENT) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        Car c1 = new Car(1, "G09");
        p.park(c1);

        assertNotEquals(PLEventEnum.EIGHTYCENT, per1.checkStatus());
        assertEquals(PLEventEnum.EIGHTYCENT, per2.checkStatus());
        assertEquals(PLEventEnum.EIGHTYCENT, per3.checkStatus());

    }



}