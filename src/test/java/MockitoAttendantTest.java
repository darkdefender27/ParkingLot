import org.bmag.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by Shubham Utwal on 7/29/2015.
 */
public class MockitoAttendantTest {


    @BeforeClass
    public void setUp() {



    }

    @Test
    public void testMaxCapacityParkingLotSucceed() {

        ParkingSpace mockedSpace = mock(ParkingSpace.class);
        Attendant attendant = new Attendant(mockedSpace);

        Car c1 = new Car(1, "G07");
        String tokenGenerated1 = attendant.park(c1);



    }



}
